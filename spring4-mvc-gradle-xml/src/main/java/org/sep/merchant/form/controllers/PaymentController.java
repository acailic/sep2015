package org.sep.merchant.form.controllers;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

import org.sep.merchant.form.dto.MerchantResponseDTO;
import org.sep.merchant.form.dto.PaymentDTO;
import org.sep.merchant.form.dto.PriceDTO;
import org.sep.merchant.form.dto.WholeInsuranceDTO;
import org.sep.merchant.form.model.Merchant;
import org.sep.merchant.form.model.Order;
import org.sep.merchant.form.service.MerchantService;
import org.sep.merchant.form.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PaymentController {
	
	private final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	MerchantService merchantService;
	
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView confirmInsurance() {
		logger.info("Confirming insurance...");
		try{
			Merchant merchant = merchantService.findAll().get(0); //preuzimanje merchanta iz baze
			Order order = new Order();
			PaymentDTO paymentDTO = new PaymentDTO(new Long(merchant.getId()), merchant.getPassword(), new Double(12000.23), 
					new Long(1), new Date(2,2,2015), "error");
			
			RestTemplate temp = new RestTemplate();
	        temp.setErrorHandler(new DefaultResponseErrorHandler(){
	            protected boolean hasError(HttpStatus statusCode) {
	                return false;
	            }
	        });

	        ObjectMapper mapper = new ObjectMapper();

	        ResponseEntity<String> response = temp.postForEntity("http://localhost:8081/Acquirer_back/payment",
	                                                    mapper.writeValueAsString(paymentDTO), String.class);

	        MerchantResponseDTO responseDto = mapper.readValue(response.getBody(), MerchantResponseDTO.class);
	
	
		
		} catch (Exception e){
			logger.error(e.toString());
			return new ModelAndView("redirect:/confirm");
		}
		
		return new ModelAndView("redirect:/myURL");
	
	}
	
	@RequestMapping(value = "/calculate", method = RequestMethod.POST/*, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE*/)
    public ResponseEntity<?> calculatePrice(@RequestBody WholeInsuranceDTO insurance) {
		logger.info("Calculating price of insurance...");
		try{
			PriceDTO priceDTO = new PriceDTO();
			BigDecimal basicPrice = new BigDecimal(1); //1EUR je cena osiguranja po danu
			
			if(insurance.getTravel().getDuration() == "" || insurance.getTravel().getDuration() == null){
				if(insurance.getTravel().getStart_date() == null){
					logger.error("Duration not set, as well as the start date.");
					return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
				} else if(insurance.getTravel().getEnd_date() == null){
					logger.error("Duration not set, as well as the end date.");
					return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
				}
				long diff = insurance.getTravel().getEnd_date().getTime() - insurance.getTravel().getStart_date().getTime();
			    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			    basicPrice = basicPrice.multiply(new BigDecimal(days));
			} else 
				basicPrice = basicPrice.multiply(new BigDecimal(insurance.getTravel().getDuration())); //mnozi se sa brojem dana
			
			
			return new ResponseEntity<PriceDTO>(priceDTO, HttpStatus.OK) ;
		} catch(Exception e){
			logger.error(e.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
