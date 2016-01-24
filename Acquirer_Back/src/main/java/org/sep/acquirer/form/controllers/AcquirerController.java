package org.sep.acquirer.form.controllers;


import org.sep.acquirer.form.dto.AcquirerDTO;
import org.sep.acquirer.form.model.Acquirer;
import org.sep.acquirer.form.service.AcquirerService;
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


@Controller
@RequestMapping(value = "/acquirer")
public class AcquirerController {

	private final Logger logger = LoggerFactory.getLogger(AcquirerController.class);
	  
	@Autowired
	private AcquirerService acquirerService;
	        
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> setupAcquirer(@RequestBody AcquirerDTO acquirerDTO) {
		logger.info("SetupAcquirer called");
		Acquirer newAcquirer = new Acquirer();
//		newAcquirer.setAddress(acquirerDTO.getAddress());
//		newAcquirer.setEmail(acquirerDTO.getEmail());
//		newAcquirer.setIin(acquirerDTO.getIin());
//		newAcquirer.setName(acquirerDTO.getName());
//		newAcquirer.setTelNumber(acquirerDTO.getTelNumber());
//		newAcquirer.setUrl(acquirerDTO.getUrl());
		newAcquirer.setName("IntesaBank");
		newAcquirer.setUrl("nekiurl");
		newAcquirer.setIin("006699");  
		try{
			newAcquirer = acquirerService.save(newAcquirer);
			logger.info("Entity saved!");
			return new ResponseEntity<Acquirer>(newAcquirer, HeaderUtil.getHeader(), HttpStatus.OK);
		}catch(Exception ex){

			logger.error(ex.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }	
}
