package org.sep.acquirer.form.controllers;

import java.util.Date;

import org.sep.acquirer.form.dto.AcquirerOrderDTO;
import org.sep.acquirer.form.dto.PaymentDTO;
import org.sep.acquirer.form.dto.PaymentResponseDTO;
import org.sep.acquirer.form.dto.TransactionDTO;
import org.sep.acquirer.form.model.AcquirerOrder;
import org.sep.acquirer.form.model.AcquirerOrder.OrderStateEnum;
import org.sep.acquirer.form.model.Merchant;
import org.sep.acquirer.form.model.Payment;
import org.sep.acquirer.form.service.AcquirerOrderService;
import org.sep.acquirer.form.service.AcquirerService;
import org.sep.acquirer.form.service.MerchantService;
import org.sep.acquirer.form.service.PaymentService;
import org.sep.acquirer.form.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

	private final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	  
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private AcquirerService acquirerService;
	@Autowired
	private AcquirerOrderService acquirerOrderService;
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> paymentRequest(@RequestBody PaymentDTO paymentDTO) {
		logger.info("SetupAcquirer called");
		
		if(paymentDTO.getMerchantId() == null)
			return new ResponseEntity<String>("Merchant ID is null!", HttpStatus.BAD_GATEWAY);
		
		Merchant merchant = merchantService.find(paymentDTO.getMerchantId());
		
		if(merchant == null)
			return new ResponseEntity<String>("Merchant does not exist!", HttpStatus.BAD_GATEWAY);
		
		if(!merchant.getPassword().equals(paymentDTO.getMerchantPass()))
			return new ResponseEntity<String>("Merchant pass is invalid!", HttpStatus.BAD_GATEWAY);
		
		Payment payment = new Payment(paymentDTO, merchant);
		PaymentResponseDTO response = new PaymentResponseDTO("fillPaymentURL", payment.getId());
		try{
			payment = paymentService.save(payment);
			logger.info("Payment saved!");
			return new ResponseEntity<PaymentResponseDTO>(response, HeaderUtil.getHeader(), HttpStatus.OK);
		}catch(Exception ex){

			logger.error(ex.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@RequestMapping(value="/confirm", method = RequestMethod.POST, 
					produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> confirmPayment(@RequestBody AcquirerOrderDTO orderDTO) {
		logger.info("confirm payment called");

		AcquirerOrder order = null;
		try{
//			AcquirerOrderDTO orderDTO = mapper.readValue(request, AcquirerOrderDTO.class);
			
			if(orderDTO.getPaymentId() == null ||orderDTO.getAcquirerTimeStamp() == null || orderDTO.getPan() == null ||
			   orderDTO.getCardSecCode() == null || orderDTO.getCardHolderName() == null ||
			   orderDTO.getCardExpDate() == null || orderDTO.getAmount() == null)
				return new ResponseEntity<String>("One or more params is null!", HttpStatus.BAD_GATEWAY);
			
			Payment payment = paymentService.find(orderDTO.getPaymentId());
			if(payment == null)
				return new ResponseEntity<String>("Payment does not exist!", HttpStatus.BAD_GATEWAY);
			
			if(!payment.getAmount().equals(orderDTO.getAmount()))
				return new ResponseEntity<String>("Amount in order difers from amount in payment!", HttpStatus.BAD_GATEWAY);
			
			
	
			order = new AcquirerOrder(orderDTO, payment, OrderStateEnum.PENDING);
			order = acquirerOrderService.save(order);
			TransactionDTO transaction = new TransactionDTO(order);
			
			logger.info("Order saved!");
					
			String requestUrl        = "http://localhost:8082/PCC/transaction";
			
			RestTemplate temp = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			ResponseEntity<String> response = temp.postForEntity(requestUrl, mapper.writeValueAsString(transaction), String.class);
			
			
			if(response.getStatusCode() == HttpStatus.OK){
				
				//PccResponseDTO
				//update acquirerOrder with new data
//				return new ResponseEntity<Object>(o, HeaderUtil.getHeader(), HttpStatus.OK);
				return new ResponseEntity<Object>(HttpStatus.OK);
				
			}else{	
				order.setOrderState(OrderStateEnum.UNSUCCESSFULL);
				acquirerOrderService.save(order);
				logger.error("HTTP State: " + response.getStatusCode());
				return new ResponseEntity<String>(payment.getMerchantErrorURL(), HeaderUtil.getHeader(), HttpStatus.BAD_GATEWAY);
			}
		}catch(Exception ex){
			logger.error(ex.toString());
			if(order != null)
				order.setOrderState(OrderStateEnum.UNSUCCESSFULL);
			
			try {
				if(order != null)
					acquirerOrderService.save(order);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			
		}
    }
	
}
