package org.sep.merchant.form.controllers;

import java.util.List;

import org.sep.merchant.form.model.Traveler;
import org.sep.merchant.form.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TravelerController {
	
	@Autowired
	TravelerService travelerService;
	
	@RequestMapping(value = "/traveler/", method = RequestMethod.GET)
    public ResponseEntity<List<Traveler>> listAllTravelers() {
        List<Traveler> travelers = (List<Traveler>) travelerService.findAll();
        if(travelers.isEmpty()){
            return new ResponseEntity<List<Traveler>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Traveler>>(travelers, HttpStatus.OK);
    }
	

}
