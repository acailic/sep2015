package org.sep.acquirer.form.controllers;

import java.security.SecureRandom;

import org.sep.acquirer.form.dto.AcquirerOrderDTO;
import org.sep.acquirer.form.dto.AcquirerResponseDTO;
import org.sep.acquirer.form.dto.PaymentDTO;
import org.sep.acquirer.form.dto.PaymentResponseDTO;
import org.sep.acquirer.form.dto.PccResponseDTO;
import org.sep.acquirer.form.dto.TokenResponseDTO;
import org.sep.acquirer.form.dto.TransactionDTO;
import org.sep.acquirer.form.model.AcquirerOrder;
import org.sep.acquirer.form.model.AcquirerOrder.OrderStateEnum;
import org.sep.acquirer.form.model.Merchant;
import org.sep.acquirer.form.model.Payment;
import org.sep.acquirer.form.service.AcquirerOrderService;
import org.sep.acquirer.form.service.AcquirerService;
import org.sep.acquirer.form.service.MerchantService;
import org.sep.acquirer.form.service.PaymentService;
import org.sep.acquirer.form.util.AcquirerConstants;
import org.sep.acquirer.form.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/payment")
@CrossOrigin(origins = "http://localhost:8000")
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

	private static final String DECLINE = "Aquirer Declined Transaction - reason: ";
	
	
	@RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> paymentRequest(@RequestBody String request) {
		logger.info("SetupAcquirer called");
		ObjectMapper mapper = new ObjectMapper();

		try{
			PaymentDTO paymentDTO = mapper.readValue(request, PaymentDTO.class);
			
			
			if(paymentDTO.getMerchantId() == null){
				logger.info(DECLINE + "Merchant ID is null!");
				return new ResponseEntity<String>("Merchant ID is null!", HttpStatus.BAD_GATEWAY);
			}
			
			Merchant merchant = merchantService.find(paymentDTO.getMerchantId());		
			if(merchant == null){
				logger.info(DECLINE + "Merchant does not exist!");
				return new ResponseEntity<String>("Merchant does not exist!", HttpStatus.BAD_GATEWAY);
			}
			if(!merchant.getPassword().equals(paymentDTO.getMerchantPass())){
				logger.info(DECLINE + "Merchant pass is invalid!");
				return new ResponseEntity<String>("Merchant pass is invalid!", HttpStatus.BAD_GATEWAY);
			}
			
			Payment payment = new Payment(paymentDTO, merchant);
			payment = paymentService.save(payment);
			PaymentResponseDTO response = new PaymentResponseDTO(AcquirerConstants.ACQUIRER_WEB_APP_HOST + 
				 											 AcquirerConstants.ACQUIRER_WEB_APP_PAYMENT_URL , payment.getId());
		
			logger.info("Payment saved!");
			return new ResponseEntity<PaymentResponseDTO>(response, HeaderUtil.getHeader(), HttpStatus.OK);
		}catch(Exception ex){

			logger.error(ex.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@RequestMapping(value = "init/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> initPayment(@RequestParam("id") Long paymentID) {
		logger.info("Init called");

		try{
			if(paymentID == null){
				logger.info(DECLINE + "Payment ID is null!");
				return new ResponseEntity<String>("Payment ID is null!", HttpStatus.BAD_GATEWAY);
			}
			
			Payment payment = paymentService.find(paymentID);		
			if(payment == null){
				logger.info(DECLINE + "Payment does not exist!");
				return new ResponseEntity<String>("Payment does not exist!", HttpStatus.BAD_GATEWAY);
			}
			
			if(payment.getCSRFToken() != null){
				logger.info(DECLINE + "Payment has already been procceded!");
				return new ResponseEntity<String>("Payment has already been procceded!", HttpStatus.BAD_GATEWAY);
			}
			
			SecureRandom scrfToken = new SecureRandom();			
			payment.setCSRFToken(String.valueOf(scrfToken.nextInt()));
			payment = paymentService.update(payment);
			
			TokenResponseDTO response = new TokenResponseDTO(payment.getId(), payment.getAmount(), payment.getCSRFToken());
			
			return new ResponseEntity<TokenResponseDTO>(response, HeaderUtil.getHeaderResponseAcquirer(), HttpStatus.OK);
		}catch(Exception ex){

			logger.error(ex.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@RequestMapping(value="/confirm", method = RequestMethod.POST, 
					produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> confirmPayment(@RequestBody AcquirerOrderDTO orderDTO) {
		logger.info("confirm payment called");

		logger.info("CSRF token: " + orderDTO.getCSRFToken());
		AcquirerOrder order = null;
		RestTemplate temp = new RestTemplate();
		temp.setErrorHandler(new DefaultResponseErrorHandler(){
		    protected boolean hasError(HttpStatus statusCode) {
		        return false;
		    }
		});
		
		try{
			if(orderDTO.getPaymentId() == null || orderDTO.getAcquirerTimeStamp() == null || 
			   orderDTO.getPan() == null || orderDTO.getPan().isEmpty() ||
			   orderDTO.getCardSecCode() == null || orderDTO.getCardSecCode().isEmpty() ||
			   orderDTO.getCardHolderName() == null ||  orderDTO.getCardHolderName().isEmpty() ||
			   orderDTO.getCardExpDate() == null || orderDTO.getAmount() == null /*||
			   orderDTO.getCSRFToken() == null*/){
				logger.info(DECLINE + "Request is incomplete!");
				return new ResponseEntity<String>("Request is incomplete!", HttpStatus.BAD_GATEWAY);
			}
			
			Payment payment = paymentService.find(orderDTO.getPaymentId());
			if(payment == null){
				logger.info(DECLINE + "Payment does not exist!");
				return new ResponseEntity<String>("Payment does not exist!", HttpStatus.BAD_GATEWAY);
			}
						
			if(!payment.getAmount().equals(orderDTO.getAmount())){
				logger.info(DECLINE + "Amount in Order difers from amount in Payment!");
				return new ResponseEntity<String>("Amount in Order difers from amount in Payment!", HttpStatus.BAD_GATEWAY);
			}
			logger.info(payment.getCSRFToken() + "_" + orderDTO.getCSRFToken());
			//TODO: orderDTO.getCSRFToken() uvek vraca null, sa fronta se posalje dobra vrednost
//			if(!payment.getCSRFToken().equals(orderDTO.getCSRFToken())){
//				logger.info(DECLINE + "CSRF Token is invalid!");
//				return new ResponseEntity<String>("CSRF Token is invalid!", HttpStatus.BAD_GATEWAY);
//			}
	
			order = new AcquirerOrder(orderDTO, payment, OrderStateEnum.PENDING);
			order = acquirerOrderService.save(order);
			TransactionDTO transaction = new TransactionDTO(order);
			
			logger.info("Order saved!");
					
			
			
			ObjectMapper mapper = new ObjectMapper();
			ResponseEntity<String> response = temp.postForEntity(AcquirerConstants.PCC_HOST + AcquirerConstants.PCC_API,
													mapper.writeValueAsString(transaction), String.class);

			logger.info("After response creation");
						
			AcquirerResponseDTO result = new AcquirerResponseDTO();
			result.setAcquirerOrderId(order.getId());
			result.setAcquirerTimeStamp(order.getAcquirerTimestamp());
			result.setMerchantOrderId(order.getPayment().getMerchantOrderId());
			result.setMerchantTimeStamp(order.getPayment().getMerchantTimestamp());
			result.setPaymentId(order.getPayment().getId());
			result.setErrorUrl(order.getPayment().getMerchantErrorURL());
						
			if(response.getStatusCode() == HttpStatus.OK){
				
				PccResponseDTO responseDto = mapper.readValue(response.getBody(), PccResponseDTO.class);
				logger.info("Acquirer received PCC's response");
				
				order.setOrderState(responseDto.getState());
				logger.info("Order id:" + order.getId() + " Payment id:" + order.getPayment().getId());
				order = acquirerOrderService.update(order);
				
				result.setState(order.getOrderState());
				
				return new ResponseEntity<AcquirerResponseDTO>(result, HeaderUtil.getHeaderResponseAcquirer(), HttpStatus.OK);

			}else{	
				PccResponseDTO responseDto = mapper.readValue(response.getBody(), PccResponseDTO.class);
				logger.error("Acquirer received error - HTTP State: " + response.getStatusCode() + " Msg: " + responseDto.getErrorMsg());
				order.setOrderState(OrderStateEnum.UNSUCCESSFULL);
				order = acquirerOrderService.update(order);
				result.setState(order.getOrderState());
				result.setErrorMsg(responseDto.getErrorMsg());
				
				return new ResponseEntity<AcquirerResponseDTO>(result, HeaderUtil.getHeaderResponseAcquirer(), response.getStatusCode());
			}
		}catch(Exception ex){
			
			logger.error(ex.toString());			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			
		}
    }
	
}
