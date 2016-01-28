package org.sep.pcc.form.controllers;


import org.sep.pcc.form.dto.BankDTO;
import org.sep.pcc.form.model.Bank;
import org.sep.pcc.form.service.BankService;
import org.sep.pcc.form.util.HeaderUtil;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/bank")
public class BankController {

	private final Logger logger = LoggerFactory.getLogger(BankController.class);
	  
	@Autowired
	private BankService bankService;
	        
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
    public @ResponseBody ResponseEntity<?> registerBank(@RequestBody BankDTO bankDTO) {
		logger.info("Register Bank called");
		Bank newBank = new Bank(bankDTO);
		
		try{
			newBank = bankService.save(newBank);
			logger.info("Bank registered!");
			return new ResponseEntity<Bank>(newBank, HeaderUtil.getHeader(), HttpStatus.OK);
		}catch(Exception ex){

			logger.error(ex.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> getAcquirer(@RequestParam("id") Long id) {
		
		try{
			logger.info(bankService.find(id).getName());
			return new ResponseEntity<Bank>(bankService.find(id), HeaderUtil.getHeader(), HttpStatus.OK);
		}catch(Exception ex){

			logger.error(ex.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }	
}
