package org.sep.merchant.form.controllers;

import java.util.ArrayList;
import java.util.List;

import org.sep.merchant.form.dto.CarDTO;
import org.sep.merchant.form.dto.HomeDTO;
import org.sep.merchant.form.dto.HumanAgeDTO;
import org.sep.merchant.form.dto.InsuranceDTO;
import org.sep.merchant.form.dto.OwnerDTO;
import org.sep.merchant.form.dto.TravelerDTO;
import org.sep.merchant.form.dto.VehicleDTO;
import org.sep.merchant.form.dto.WholeInsuranceDTO;
import org.sep.merchant.form.model.Home;
import org.sep.merchant.form.model.Insurance;
import org.sep.merchant.form.model.InsuranceOwner;
import org.sep.merchant.form.model.Owner;
import org.sep.merchant.form.model.RiskItemInsurance;
import org.sep.merchant.form.model.Traveler;
import org.sep.merchant.form.model.TravelerInsurance;
import org.sep.merchant.form.model.Vehicle;
import org.sep.merchant.form.service.HomeService;
import org.sep.merchant.form.service.InsuranceOwnerService;
import org.sep.merchant.form.service.InsuranceService;
import org.sep.merchant.form.service.OwnerService;
import org.sep.merchant.form.service.RiskItemInsuranceService;
import org.sep.merchant.form.service.RiskItemService;
import org.sep.merchant.form.service.TravelerInsuranceService;
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
	
	@Autowired
	OwnerService ownerService;
	
	@Autowired
	RiskItemService riskItemService;
	
	@Autowired
	RiskItemInsuranceService riskItemInsuranceService;
	
	@Autowired
	TravelerInsuranceService travelerInsuranceService;
	
	//metoda za testiranje
	@RequestMapping(value = "/lol", method = RequestMethod.GET)
    public ResponseEntity<Traveler> getInsurance() {
        Traveler travelers = new Traveler("igor", "jovin", "1654", "15165", "aSDHKS", "1564");
        System.out.println("Usao");
        return new ResponseEntity<Traveler>(travelers, HeaderUtil.getHeader(), HttpStatus.OK);
    }
	
	/**Kreiranje novog osiguranja, kao i cuvanje putnika i informacija o dodatnim stvarima, poput osiguranja kuce i vozila*/
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> createInsurance(@RequestBody WholeInsuranceDTO insurance,
    											UriComponentsBuilder ucBuilder) {
        System.out.println("Creating insurance.");
        
        InsuranceDTO travel = insurance.getTravel();
        Insurance insuranceToPersist = new Insurance(travel.getDuration(), travel.getStart_date(), travel.getEnd_date(), 
        		insurance.getTravellers().size());
        //obracunati cenu osiguranja
        //spajanje sa PriceListItem
        //spajanje sa Order - ne ovde
        
        //odredjivanje Owner-a osiguranja
        OwnerDTO ownerOfInsurance = travel.getOwner();
        InsuranceOwner ownerOfInsuranceToPersist = new InsuranceOwner(ownerOfInsurance.getFirst_name(), 
        		ownerOfInsurance.getLast_name(), "0", ownerOfInsurance.getJmbg(), ownerOfInsurance.getAddress(), 
        		"000", ownerOfInsurance.getEmail());
        insuranceOwnerService.save(ownerOfInsuranceToPersist);
        
        insuranceToPersist.setInsuranceOwner(ownerOfInsuranceToPersist);
        insuranceService.save(insuranceToPersist);
        
        //povezivanje sa RiskItemima
		if (travel.getRegion_id() != null) {
			RiskItemInsurance connectionRegion = new RiskItemInsurance(riskItemService.find(travel.getRegion_id()),
					insuranceToPersist);
			riskItemInsuranceService.save(connectionRegion);
		}
		
		if (travel.getMax_value_id() != null) {
			RiskItemInsurance connectionMaxValue = new RiskItemInsurance(riskItemService.find(travel.getMax_value_id()),
					insuranceToPersist);
			riskItemInsuranceService.save(connectionMaxValue);
		}
		
		if (travel.getSport_id() != null) {
			RiskItemInsurance connectionSport = new RiskItemInsurance(riskItemService.find(travel.getSport_id()),
					insuranceToPersist);
			riskItemInsuranceService.save(connectionSport);
		}
		
		List<HumanAgeDTO> humanAges = travel.getHuman_age();
		if(!humanAges.isEmpty()){
			RiskItemInsurance connectionAge;
			for(HumanAgeDTO age : humanAges){
				connectionAge = new RiskItemInsurance(riskItemService.find(age.getId()),
						insuranceToPersist);
				riskItemInsuranceService.save(connectionAge);
			}
			
		}
		
		if(insurance.getHome() != null){ //ako je kupac zeleo i osiguranje za kucu
			HomeDTO homeDTO = insurance.getHome();
			Home home = new Home(homeDTO.getFloor_area(), homeDTO.getFlat_age(), homeDTO.getEst_value());
			OwnerDTO ownerDTO = homeDTO.getOwner();
			Owner ownerToPersist = new Owner(ownerDTO.getFirst_name(), ownerDTO.getLast_name(), ownerDTO.getJmbg());
			ownerService.save(ownerToPersist);
			home.setOwner(ownerToPersist);
			homeService.save(home);
		}
		
		if(insurance.getVehicle() != null){ //ako je kupac zeleo i osiguranje za vozilo
			VehicleDTO vehicleDTO = insurance.getVehicle();
			CarDTO carDTO = vehicleDTO.getCar();
			Vehicle vehicle = new Vehicle(carDTO.getType(), carDTO.getMan_year(), carDTO.getReg_num(), carDTO.getChassis_num());
			OwnerDTO ownerCarDTO = vehicleDTO.getOwner();
			Owner ownerCarToPersist = new Owner(ownerCarDTO.getFirst_name(), ownerCarDTO.getLast_name(), ownerCarDTO.getJmbg());
			ownerService.save(ownerCarToPersist);
			vehicle.setOwner(ownerCarToPersist);
			vehicleService.save(vehicle);
			
			//+++ RIZICI
		}
		
		List<TravelerDTO> travellers = new ArrayList<TravelerDTO>();
		travellers = insurance.getTravellers();
		
		if(travellers.isEmpty())
			return null;
		
		//zasto u traveleru ima id-ja za human age, a ima i u travel???
		Traveler travelerToPersist;
		for(TravelerDTO travelerDTO : travellers){
			travelerToPersist = new Traveler(travelerDTO.getFirst_name(), travelerDTO.getLast_name(), 
					travelerDTO.getPassport_num(), travelerDTO.getJmbg(), travelerDTO.getAddress(), 
					travelerDTO.getTel_num());
			travelerService.save(travelerToPersist);
			
			//++++ RIZICI
			TravelerInsurance ti = new TravelerInsurance(travelerToPersist, insuranceToPersist);
			travelerInsuranceService.save(ti);
		}
  
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/insurance/{id}").buildAndExpand(insuranceToPersist.getId()).toUri());
        //headers.
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
