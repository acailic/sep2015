package org.sep.merchant.form.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.sep.merchant.form.dto.HomeDTO;
import org.sep.merchant.form.dto.HumanAgeDTO;
import org.sep.merchant.form.dto.InsuranceDTO;
import org.sep.merchant.form.dto.MerchantResponseDTO;
import org.sep.merchant.form.dto.PaymentDTO;
import org.sep.merchant.form.dto.PriceDTO;
import org.sep.merchant.form.dto.ReturnUrlDTO;
import org.sep.merchant.form.dto.TransactionResultDTO;
import org.sep.merchant.form.dto.VehicleDTO;
import org.sep.merchant.form.dto.WholeInsuranceDTO;
import org.sep.merchant.form.model.Merchant;
import org.sep.merchant.form.model.Order;
import org.sep.merchant.form.model.RiskItem;
import org.sep.merchant.form.service.MerchantService;
import org.sep.merchant.form.service.OrderService;
import org.sep.merchant.form.service.RiskItemService;
import org.sep.merchant.form.util.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@Autowired
	RiskItemService riskItemService;
	
	@Autowired
    JavaMailSender mailSender;
	
	@RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET)
    public ModelAndView confirmInsurance(@PathVariable("id") Integer id) {
		logger.info("Confirming insurance...");
		try{
			Merchant merchant = merchantService.findAll().get(0); //preuzimanje merchanta iz baze
			Order order = orderService.find(id);
			if(order == null)
				return new ModelAndView("redirect:/confirm");
			PaymentDTO paymentDTO = new PaymentDTO(new Long(merchant.getId()), merchant.getPassword(), order.getAmount(), 
					new Long(order.getId()), order.getMerchantTimestamp(), order.getErrorUrl());
			
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
	        
			return new ModelAndView("redirect:" + responseDto.getPaymentUrl() + "/" + responseDto.getPaymentId());
		} catch (Exception e){
			logger.error(e.toString());
			return new ModelAndView("redirect:/https://localhost:8001/error.html"); //DOPUNITI
		}
	}
	
	@RequestMapping(value = "/transactionResults", method = RequestMethod.POST)
	public ResponseEntity<?> getTransactionResults(@RequestBody TransactionResultDTO transactionResult){
		try{
			Order order = orderService.find(transactionResult.getMerchantOrderId());
			if(order == null)
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			
			String redirectUrl = order.getSuccessUrl();
			if(!transactionResult.getTransactionResult().equalsIgnoreCase("success")){
				if(transactionResult.getTransactionResult().equalsIgnoreCase("fail"))
					redirectUrl = order.getFailedUrl();
				else
					redirectUrl = order.getErrorUrl();
			} 
			
			EmailValidator validator = new EmailValidator();
			boolean validationResult = validator.validate(order.getInsurance().getInsuranceOwner().getEmail());
			
			java.util.Date currentDate = new Date();
			java.sql.Date currentDateSql = new java.sql.Date(currentDate.getTime());
			java.sql.Date acquirerTimestamp = new java.sql.Date(transactionResult.getAcquirerTimestamp());
			java.sql.Date timestampLimit = new java.sql.Date(currentDate.getTime());
			timestampLimit.setMinutes(currentDateSql.getMinutes() + 10);
			boolean timeResult = true;
			if(acquirerTimestamp.before(currentDateSql) || acquirerTimestamp.after(timestampLimit))
				timeResult = false;
			
			if(!validationResult && !timeResult)
				redirectUrl = order.getFailedUrl();
			
			// creates a simple e-mail object
	        SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(order.getInsurance().getInsuranceOwner().getEmail());
	        email.setSubject("Potvrda kupovine osiguranja");
	        email.setText("Ovim emailom potvrdjujemo da ste kupili osiguranje. ID Vase transakcije je " 
	        		+ transactionResult.getPaymentId());
	         
	        // sends the e-mail
	        mailSender.send(email);
	        ReturnUrlDTO returnUrlDTO = new ReturnUrlDTO(redirectUrl);
			return new ResponseEntity<ReturnUrlDTO>(returnUrlDTO, HttpStatus.OK);
		} catch (Exception e){
			logger.error(e.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/calculateTravel", method = RequestMethod.POST/*, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE*/)
    public ResponseEntity<?> calculateTravelPrice(@RequestBody InsuranceDTO insurance) {
		logger.info("Calculating price of travel insurance...");
		try{
			PriceDTO priceDTO = new PriceDTO();
			BigDecimal priceNum = new BigDecimal(0);
			priceNum = determineTravelPrice(insurance);
			if(!priceNum.equals(new BigDecimal(-1))){
				priceNum = priceNum.setScale(2, RoundingMode.CEILING);
				priceDTO.setTravel_price(priceNum);
			} else
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<PriceDTO>(priceDTO, HttpStatus.OK) ;
		} catch (Exception e){
			logger.error(e.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/calculateVehicle", method = RequestMethod.POST/*, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE*/)
    public ResponseEntity<?> calculateVehiclePrice(@RequestBody VehicleDTO insurance) {
		logger.info("Calculating price of vehicle insurance...");
		try{
			PriceDTO priceDTO = new PriceDTO();
			BigDecimal priceNum = new BigDecimal(0);
			if(!(priceNum = determineVehiclePrice(insurance)).equals(new BigDecimal(-1))){
				priceNum = priceNum.setScale(2, RoundingMode.CEILING);
				priceDTO.setVehicle_price(priceNum);
			} else
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<PriceDTO>(priceDTO, HttpStatus.OK) ;
		} catch (Exception e){
			logger.error(e.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/calculateHome", method = RequestMethod.POST/*, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE*/)
    public ResponseEntity<?> calculateHomePrice(@RequestBody HomeDTO insurance) {
		logger.info("Calculating price of home insurance...");
		try{
			PriceDTO priceDTO = new PriceDTO();
			BigDecimal priceNum = new BigDecimal(0);
			if(!(priceNum = determineHomePrice(insurance)).equals(new BigDecimal(-1))){
				priceNum = priceNum.setScale(2, RoundingMode.CEILING);
				priceDTO.setHome_price(priceNum);
			} else
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<PriceDTO>(priceDTO, HttpStatus.OK) ;
		} catch (Exception e){
			logger.error(e.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/calculate", method = RequestMethod.POST/*, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE*/)
    public ResponseEntity<?> calculatePrice(@RequestBody WholeInsuranceDTO insurance) {
		logger.info("Calculating price of insurance...");
		try{
			PriceDTO priceDTO = new PriceDTO();
			if(insurance.getHome() != null){
				BigDecimal homePrice = determineHomePrice(insurance.getHome());
				homePrice = homePrice.setScale(2, RoundingMode.CEILING);
				priceDTO.setHome_price(homePrice);
			}
			if(insurance.getVehicle() != null){
				BigDecimal vehiclePrice = determineVehiclePrice(insurance.getVehicle());
				vehiclePrice = vehiclePrice.setScale(2, RoundingMode.CEILING);
				priceDTO.setVehicle_price(vehiclePrice);
			}
			BigDecimal travelPrice = determineTravelPrice(insurance.getTravel());
			travelPrice = travelPrice.setScale(2, RoundingMode.CEILING);
			priceDTO.setTravel_price(travelPrice);
			return new ResponseEntity<PriceDTO>(priceDTO, HttpStatus.OK) ;
		} catch(Exception e){
			logger.error(e.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public BigDecimal determineRiskItemPrice(Integer riskItemId, BigDecimal basicPrice){
		try{
			BigDecimal riskPrice = basicPrice;
			RiskItem riskItem = riskItemService.find(riskItemId);
			if(riskItem == null || basicPrice.equals(BigDecimal.ZERO))
				return new BigDecimal(-1);
			riskPrice = riskPrice.multiply(new BigDecimal(riskItem.getFactor()));
			return riskPrice;
		} catch (Exception e){
			logger.error(e.toString());
			return new BigDecimal(-1);
		}
	}
	
	public BigDecimal determineHomePrice(HomeDTO home){
		BigDecimal homePrice = new BigDecimal(1.2); //1.2 EUR je cena osiguranja kuce po danu
		
		if(home.getDuration() == "" || home.getDuration() == null){
			if(home.getStart_date() == null){
				logger.error("Duration not set, as well as the start date for the home insurance.");
				return new BigDecimal(-1);
			} else if(home.getEnd_date() == null){
				logger.error("Duration not set, as well as the end date for the home insurance.");
				return new BigDecimal(-1);
			}
			long diff = home.getEnd_date().getTime() - home.getStart_date().getTime();
		    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		    homePrice = homePrice.multiply(new BigDecimal(days));
		} else 
			homePrice = homePrice.multiply(new BigDecimal(home.getDuration()));
		
		BigDecimal floorAreaFactor = homePrice.multiply(new BigDecimal(home.getFloor_area()/100)); //povrsina kuce povecava cenu
		BigDecimal flatAgeFactor = homePrice.multiply(new BigDecimal(home.getFlat_age()/10));
		BigDecimal estValueFactor = homePrice.multiply(new BigDecimal(home.getFloor_area()/100000)); //procenjena cena kuce povecava cenu
		homePrice.add(floorAreaFactor);
		homePrice.add(estValueFactor);
		homePrice.add(flatAgeFactor);
		
		if(home.getCasualty_ids().size() != 0 || home.getCasualty_ids() != null){
			for(Integer casualtyId : home.getCasualty_ids()){
				if(!determineRiskItemPrice(casualtyId, homePrice).equals(new BigDecimal(-1)))
					homePrice = homePrice.add(determineRiskItemPrice(casualtyId, homePrice));
				else
					return new BigDecimal(-1);
			}
		}
		return homePrice;
	}
	
	public BigDecimal determineVehiclePrice(VehicleDTO vehicle){
		BigDecimal vehiclePrice = new BigDecimal(1.1); //1.1 EUR je cena osiguranja kuce po danu
		
		if(vehicle.getDuration() == "" || vehicle.getDuration() == null){
			if(vehicle.getStart_date() == null){
				logger.error("Duration not set, as well as the start date for the vehicle insurance.");
				return new BigDecimal(-1);
			} else if(vehicle.getEnd_date() == null){
				logger.error("Duration not set, as well as the end date for the vehicle insurance.");
				return new BigDecimal(-1);
			}
			long diff = vehicle.getEnd_date().getTime() - vehicle.getStart_date().getTime();
		    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		    vehiclePrice = vehiclePrice.multiply(new BigDecimal(days));
		} else 
			vehiclePrice = vehiclePrice.multiply(new BigDecimal(vehicle.getDuration()));
		
		if(vehicle.getTowing_id() != null){
			if(!determineRiskItemPrice(vehicle.getTowing_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(determineRiskItemPrice(vehicle.getTowing_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}
		if(vehicle.getAccomodation_id() != null){
			if(!determineRiskItemPrice(vehicle.getAccomodation_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(determineRiskItemPrice(vehicle.getAccomodation_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}
		if(vehicle.getAlternative_id() != null){
			if(!determineRiskItemPrice(vehicle.getAlternative_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(determineRiskItemPrice(vehicle.getAlternative_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}	
		if(vehicle.getRepair_id() != null){
			if(!determineRiskItemPrice(vehicle.getRepair_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(determineRiskItemPrice(vehicle.getRepair_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}	
		
		return vehiclePrice;
	}
	
	public BigDecimal determineTravelPrice(InsuranceDTO insurance){
		BigDecimal travelPrice = new BigDecimal(1); //1 EUR je cena osiguranja kuce po danu
		
		if(insurance.getDuration() == "" || insurance.getDuration() == null){
			if(insurance.getStart_date() == null){
				logger.error("Duration not set, as well as the start date for the travel insurance.");
				return new BigDecimal(-1);
			} else if(insurance.getEnd_date() == null){
				logger.error("Duration not set, as well as the end date for the travel insurance.");
				return new BigDecimal(-1);
			}
			long diff = insurance.getEnd_date().getTime() - insurance.getStart_date().getTime();
		    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		    travelPrice = travelPrice.multiply(new BigDecimal(days));
		} else 
			travelPrice = travelPrice.multiply(new BigDecimal(insurance.getDuration()));
		
		if(insurance.getRegion_id() != null && !insurance.getRegion_id().equals("")){
			if(!determineRiskItemPrice(insurance.getRegion_id(), travelPrice).equals(new BigDecimal(-1)))
				travelPrice = travelPrice.add(determineRiskItemPrice(insurance.getRegion_id(), travelPrice));
			else
				return new BigDecimal(-1);
		}
		if(insurance.getSport_id() != null && !insurance.getSport_id().equals("") && insurance.getSport_id() != 0){
			if(!determineRiskItemPrice(insurance.getSport_id(), travelPrice).equals(new BigDecimal(-1)))
				travelPrice = travelPrice.add(determineRiskItemPrice(insurance.getSport_id(), travelPrice));
			else
				return new BigDecimal(-1);
		}
		if(insurance.getMax_value_id() != null && !insurance.getMax_value_id().equals("")){
			if(!determineRiskItemPrice(insurance.getMax_value_id(), travelPrice).equals(new BigDecimal(-1)))
				travelPrice = travelPrice.add(determineRiskItemPrice(insurance.getMax_value_id(), travelPrice));
			else
				return new BigDecimal(-1);
		}	
		
		if(insurance.getHuman_age().size() != 0 || insurance.getHuman_age() != null){
			for(HumanAgeDTO age : insurance.getHuman_age()){
				if(!determineRiskItemPrice(age.getId(), travelPrice).equals(new BigDecimal(-1)))
					travelPrice = travelPrice.add(determineRiskItemPrice(age.getId(), travelPrice));
				else
					return new BigDecimal(-1);
			}
		}
		
		return travelPrice;
	}

}
