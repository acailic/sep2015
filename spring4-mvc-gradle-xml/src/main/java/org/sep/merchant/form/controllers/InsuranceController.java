package org.sep.merchant.form.controllers;

import java.util.List;

import org.sep.merchant.form.model.Home;
import org.sep.merchant.form.model.Insurance;
import org.sep.merchant.form.model.Traveler;
import org.sep.merchant.form.model.Vehicle;
import org.sep.merchant.form.service.HomeService;
import org.sep.merchant.form.service.InsuranceOwnerService;
import org.sep.merchant.form.service.InsuranceService;
import org.sep.merchant.form.service.TravelerService;
import org.sep.merchant.form.service.VehicleService;
import org.sep.merchant.form.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class InsuranceController {
	
	@Autowired
	InsuranceService insuranceService;
	
	@Autowired
	TravelerService travelerService;
	
	@Autowired
	InsuranceOwnerService insuranceOwnerService;
	
	@Autowired
	HomeService homeService;
	
	@Autowired
	VehicleService vehicleService;
	
	//metoda za testiranje
	@RequestMapping(value = "/lol", method = RequestMethod.GET)
    public ResponseEntity<Traveler> getInsurance() {
        Traveler travelers = new Traveler("igor", "jovin", "1654", "15165", "aSDHKS", "1564");
        System.out.println("Usao");
        return new ResponseEntity<Traveler>(travelers, HeaderUtil.getHeader(), HttpStatus.OK);
    }
	
	/**Kreiranje novog osiguranja, kao i cuvanje putnika i informacija o dodatnim stvarima, poput osiguranja kuce i vozila*/
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void>  createInsurance(@RequestBody Insurance insurance, 
    											@RequestBody Vehicle vehicle, 
    											@RequestBody Home home, 
    											@RequestBody List<Traveler> travelers,
    											UriComponentsBuilder ucBuilder) {
        System.out.println("Creating insurance.");
        
        insuranceService.save(insurance);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/insurance/{id}").buildAndExpand(insurance.getId()).toUri());
        
        return new ResponseEntity<Void> (headers, HttpStatus.CREATED);
    }
}
