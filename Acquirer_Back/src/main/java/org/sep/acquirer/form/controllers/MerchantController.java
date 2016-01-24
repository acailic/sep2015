package org.sep.acquirer.form.controllers;


import org.sep.acquirer.form.dto.MerchantDTO;
import org.sep.acquirer.form.model.Acquirer;
import org.sep.acquirer.form.model.Merchant;
import org.sep.acquirer.form.service.AcquirerService;
import org.sep.acquirer.form.service.MerchantService;
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
@RequestMapping(value = "/merchant")
public class MerchantController {

	private final Logger logger = LoggerFactory.getLogger(MerchantController.class);
	  
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private AcquirerService acquirerService;
	        
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> createMerchant(@RequestBody MerchantDTO merchantDTO) {
		logger.info("SetupAcquirer called");
		Merchant newMerchant = new Merchant();
		Acquirer acq = acquirerService.find(merchantDTO.getAcquirerId());
		newMerchant.setAcquirer(acq);
		
		try{
			newMerchant = merchantService.save(newMerchant);
			logger.info("Entity saved!");
			return new ResponseEntity<Merchant>(newMerchant, HeaderUtil.getHeader(), HttpStatus.OK);
		}catch(Exception ex){

			logger.error(ex.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }	
}
