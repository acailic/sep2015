package org.sep.pcc.form.controllers;

import java.util.Calendar;

import org.sep.pcc.form.dto.TransactionDTO;
import org.sep.pcc.form.model.Transaction;
import org.sep.pcc.form.model.Transaction.OrderStateEnum;
import org.sep.pcc.form.service.TransactionService;
import org.sep.pcc.form.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionController {
	

	private final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	  
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> registerBank(@RequestBody String request) {
		logger.info("new transaction entered");
		
		try{

			ObjectMapper mapper = new ObjectMapper();
			Transaction tr = new Transaction(mapper.readValue(request, TransactionDTO.class));
			
			tr = transactionService.save(tr);
			logger.info("Entity saved!");
			return new ResponseEntity<Object>(true, HeaderUtil.getHeader(), HttpStatus.OK);
		}catch(Exception ex){

			logger.error(ex.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
  }	

}
