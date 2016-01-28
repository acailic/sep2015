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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/acquirer")
@CrossOrigin(origins = "http://localhost:8000")
public class AcquirerController {

	private final Logger logger = LoggerFactory.getLogger(AcquirerController.class);
	  
	@Autowired
	private AcquirerService acquirerService;
	        
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
    public @ResponseBody ResponseEntity<?> setupAcquirer(@RequestBody AcquirerDTO acquirerDTO) {
		logger.info("SetupAcquirer called");
		Acquirer newAcquirer = new Acquirer(acquirerDTO);
		
		try{
			newAcquirer = acquirerService.save(newAcquirer);
			logger.info("Entity saved!");
			return new ResponseEntity<Acquirer>(newAcquirer, HeaderUtil.getHeader(), HttpStatus.OK);
		}catch(Exception ex){

			logger.error(ex.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> getAcquirer(@RequestParam("id") Long id) {
		
		try{
			logger.info(acquirerService.find(id).getName());
			return new ResponseEntity<Acquirer>(acquirerService.find(id), HeaderUtil.getHeader(), HttpStatus.OK);
		}catch(Exception ex){

			logger.error(ex.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }	
}
