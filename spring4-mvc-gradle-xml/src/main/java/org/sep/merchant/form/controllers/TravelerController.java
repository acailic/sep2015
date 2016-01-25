package org.sep.merchant.form.controllers;

import java.util.List;

import org.sep.merchant.form.model.Insurance;
import org.sep.merchant.form.model.Traveler;
import org.sep.merchant.form.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

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
	
	@RequestMapping(value = "/traveler/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Traveler> getTraveler(@PathVariable("id") Integer id) {
        System.out.println("Fetching Traveler with id " + id);
        Traveler traveler = travelerService.find(id);
        if (traveler == null) {
            System.out.println("Traveler with id " + id + " not found");
            return new ResponseEntity<Traveler>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Traveler>(traveler, HttpStatus.OK);
    }
	
	// kreiranje novog putnika
	@RequestMapping(value = "/addTraveler", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> createTraveler(@RequestBody Traveler traveler, UriComponentsBuilder ucBuilder) {
		System.out.println("Usao u kreiranje putnika");

		/*if (travelerService.findAll() != null) {
			if (travelerService.find(traveler.getJmbg()) != null) {
				System.out.println("An insurance with the ID "
						+ traveler.getId() + " already exists.");
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		}*/

		travelerService.save(traveler);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/traveler/{id}").buildAndExpand(traveler.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	

}
