package org.sep.merchant.form.controllers;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

import org.sep.merchant.form.dto.HomeDTO;
import org.sep.merchant.form.dto.InsuranceDTO;
import org.sep.merchant.form.dto.MerchantResponseDTO;
import org.sep.merchant.form.dto.PaymentDTO;
import org.sep.merchant.form.dto.PriceDTO;
import org.sep.merchant.form.dto.VehicleDTO;
import org.sep.merchant.form.dto.WholeInsuranceDTO;
import org.sep.merchant.form.model.Merchant;
import org.sep.merchant.form.model.Order;
import org.sep.merchant.form.model.RiskItem;
import org.sep.merchant.form.service.MerchantService;
import org.sep.merchant.form.service.OrderService;
import org.sep.merchant.form.service.RiskItemService;
import org.sep.merchant.form.util.RiskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView confirmInsurance(@RequestParam BigDecimal totalPrice) {
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
	
	@RequestMapping(value = "/calculateTravel", method = RequestMethod.POST/*, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE*/)
    public ResponseEntity<?> calculateTravelPrice(@RequestBody InsuranceDTO insurance) {
		logger.info("Calculating price of travel insurance...");
		try{
			PriceDTO priceDTO = new PriceDTO();
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
			String price = null;
			BigDecimal priceNum = new BigDecimal(0);
			if(!(priceNum = determineVehiclePrice(insurance)).equals(new BigDecimal(-1))){
				price = priceNum.toString();
			} else
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<String>(price, HttpStatus.OK) ;
		} catch (Exception e){
			logger.error(e.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/calculateHome", method = RequestMethod.POST/*, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE*/)
    public ResponseEntity<?> calculateHomePrice(@RequestBody HomeDTO insurance) {
		logger.info("Calculating price of home insurance...");
		try{
			String price = null;
			BigDecimal priceNum = new BigDecimal(0);
			if(!(priceNum = determineHomePrice(insurance)).equals(new BigDecimal(-1))){
				price = priceNum.toString();
			} else
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<String>(price, HttpStatus.OK) ;
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
		BigDecimal flatAgeFactor = new BigDecimal(0);
		BigDecimal estValueFactor = homePrice.multiply(new BigDecimal(home.getFloor_area()/100000)); //procenjena cena kuce povecava cenu
		homePrice.add(floorAreaFactor);
		homePrice.add(estValueFactor);
		
		RiskUtil riskUtil = new RiskUtil();
		if(home.getCasualty_ids().size() != 0 || !home.getCasualty_ids().equals(null)){
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
		
		RiskUtil riskUtil = new RiskUtil();
		if(!vehicle.getTowing_id().equals(null)){
			if(!determineRiskItemPrice(vehicle.getTowing_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(determineRiskItemPrice(vehicle.getTowing_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}
		if(!vehicle.getAccomodation_id().equals(null)){
			if(!determineRiskItemPrice(vehicle.getAccomodation_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(determineRiskItemPrice(vehicle.getAccomodation_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}
		if(!vehicle.getAlternative_id().equals(null)){
			if(!determineRiskItemPrice(vehicle.getAlternative_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(determineRiskItemPrice(vehicle.getAlternative_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}	
		if(!vehicle.getRepair_id().equals(null)){
			if(!determineRiskItemPrice(vehicle.getRepair_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(determineRiskItemPrice(vehicle.getRepair_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}	
		
		return vehiclePrice;
	}

}
