package org.sep.merchant.form.controllers;

import java.sql.Date;

import org.sep.merchant.form.dto.PaymentDTO;
import org.sep.merchant.form.model.Insurance;
import org.sep.merchant.form.model.Merchant;
import org.sep.merchant.form.model.Order;
import org.sep.merchant.form.service.MerchantService;
import org.sep.merchant.form.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {
	
	private final Logger logger = LoggerFactory.getLogger(RiskTypeController.class);
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	MerchantService merchantService;
	
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView confirmInsurance(@RequestBody Insurance insurance) {
		
		logger.info("Confirming insurance...");
		try{
			Merchant merchant = merchantService.findAll().get(0); //preuzimanje merchanta iz baze
			Order order = new Order();
			PaymentDTO paymentDTO = new PaymentDTO(new Long(merchant.getId()), merchant.getPassword(), new Double(12000.23), 
					new Long(1), new Date(2,2,2015), "error");
			
			
		
		} catch (Exception e){
			logger.error(e.toString());
			return new ModelAndView("redirect:/confirm");
		}
		
		return new ModelAndView("redirect:/myURL");
	
	}

}
