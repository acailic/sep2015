package org.sep.merchant.form.controllers;

import org.sep.merchant.form.model.Traveler;
import org.sep.merchant.form.service.InsuranceService;
import org.sep.merchant.form.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InsuranceController {
	
	@Autowired
	InsuranceService insuranceService;
	
	@RequestMapping(value = "/lol", method = RequestMethod.GET)
    public ResponseEntity<Traveler> getInsurance() {
        Traveler travelers = new Traveler(1, "igor", "jovin", "1654", "15165", "aSDHKS", "1564");
        /*HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "http://localhost:8000");
	    responseHeaders.set("Access-Control-Allow-Credentials", "true");
	    responseHeaders.set("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    responseHeaders.set("Access-Control-Max-Age", "3600");
	    responseHeaders.set("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");*/
        System.out.println("Usao");
        return new ResponseEntity<Traveler>(travelers, HeaderUtil.getHeader(), HttpStatus.OK);
    }
}
