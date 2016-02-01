package org.sep.merchant.form.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
import org.sep.merchant.form.model.Merchant;
import org.sep.merchant.form.model.Order;
import org.sep.merchant.form.model.Owner;
import org.sep.merchant.form.model.PriceList;
import org.sep.merchant.form.model.PriceListItem;
import org.sep.merchant.form.model.RiskItem;
import org.sep.merchant.form.model.RiskItemInsurance;
import org.sep.merchant.form.model.RiskTypeHome;
import org.sep.merchant.form.model.RiskTypeVehicle;
import org.sep.merchant.form.model.Traveler;
import org.sep.merchant.form.model.TravelerInsurance;
import org.sep.merchant.form.model.Vehicle;
import org.sep.merchant.form.service.HomeService;
import org.sep.merchant.form.service.InsuranceOwnerService;
import org.sep.merchant.form.service.InsuranceService;
import org.sep.merchant.form.service.MerchantService;
import org.sep.merchant.form.service.OrderService;
import org.sep.merchant.form.service.OwnerService;
import org.sep.merchant.form.service.PriceListItemService;
import org.sep.merchant.form.service.PriceListService;
import org.sep.merchant.form.service.RiskItemInsuranceService;
import org.sep.merchant.form.service.RiskItemService;
import org.sep.merchant.form.service.RiskTypeHomeService;
import org.sep.merchant.form.service.RiskTypeService;
import org.sep.merchant.form.service.RiskTypeVehicleService;
import org.sep.merchant.form.service.TravelerInsuranceService;
import org.sep.merchant.form.service.TravelerService;
import org.sep.merchant.form.service.VehicleService;
import org.sep.merchant.form.util.HeaderUtil;
import org.sep.merchant.form.util.PriceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class InsuranceController {
	
	private final Logger logger = LoggerFactory.getLogger(InsuranceController.class);
	
	@Autowired
	InsuranceService insuranceService;
	
	@Autowired
	TravelerService travelerService;
	
	@Autowired
	MerchantService merchantService;
	
	@Autowired
	OrderService orderService;
	
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
	
	@Autowired
	PriceListService priceListService;
	
	@Autowired
	PriceListItemService priceListItemService;
	
	@Autowired
	RiskTypeService riskTypeService;
	
	@Autowired
	RiskTypeHomeService riskTypeHomeService;
	
	@Autowired
	RiskTypeVehicleService riskTypeVehicleService;
	
	//metoda za testiranje
	@RequestMapping(value = "/lol", method = RequestMethod.GET)
    public ResponseEntity<Traveler> getInsurance() {
        Traveler travelers = new Traveler("igor", "jovin", "1654", "15165", "aSDHKS", "1564");
        System.out.println("Usao");
        return new ResponseEntity<Traveler>(travelers, HeaderUtil.getHeader(), HttpStatus.OK);
    }
	
	/**Kreiranje novog osiguranja, kao i cuvanje putnika i informacija o dodatnim stvarima, poput osiguranja kuce i vozila*/
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createInsurance(@RequestBody WholeInsuranceDTO insurance, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating insurance...");
        logger.info("Creating insurance...");
        
        try{
        InsuranceDTO travel = insurance.getTravel();
        Insurance insuranceToPersist = new Insurance(travel.getDuration(), travel.getStart_date(), travel.getEnd_date(), 
        		insurance.getTravellers().size());
        //obracunati cenu osiguranja
        PriceList priceList = new PriceList();
        priceList = priceListService.find(1);
        //if price == null return BAD_REQUEST
        BigDecimal totalPrice = PriceUtil.determineBasicPrice(insurance);
        
        //odredjivanje vlasnika osiguranja
        OwnerDTO ownerOfInsurance = travel.getOwner();
        if(ownerOfInsurance.getFirst_name() == "" || ownerOfInsurance.getFirst_name() == null ||
        		ownerOfInsurance.getLast_name() == "" || ownerOfInsurance.getLast_name() == null
        		|| ownerOfInsurance.getEmail() == "" || ownerOfInsurance.getEmail() == null
        		/*|| ownerOfInsurance.getJmbg() == null || ownerOfInsurance.getJmbg() == ""*/){
        	logger.error("Enter all the required fields of Owner.");
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
        InsuranceOwner ownerOfInsuranceToPersist = new InsuranceOwner(ownerOfInsurance.getFirst_name(), 
        		ownerOfInsurance.getLast_name(), "000", "adresa", 
        		"000", ownerOfInsurance.getEmail(), "123");
        insuranceOwnerService.save(ownerOfInsuranceToPersist);
        
        insuranceToPersist.setInsuranceOwner(ownerOfInsuranceToPersist);
        insuranceService.save(insuranceToPersist);
        
        //povezivanje sa RiskItemima
		if (travel.getRegion_id() != null) {
			logger.info("Computing region price...");
			boolean successfulConnection = connectRiskItem(travel.getRegion_id(), 
					insuranceService.find(insuranceToPersist.getId()), priceList, insurance);
			if(!successfulConnection){
				logger.error("Region price determination unsuccessful.");
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
		
		if (travel.getMax_value_id() != null) {
			logger.info("Computing maximum value price...");
			boolean successfulConnection = connectRiskItem(travel.getMax_value_id(), 
					insuranceService.find(insuranceToPersist.getId()), priceList, insurance);
			if(!successfulConnection){
				logger.error("Maximum value price determination unsuccessful.");
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
		
		if (travel.getSport_id() != null) {
			logger.info("Computing sport price...");
			boolean successfulConnection = connectRiskItem(travel.getSport_id(), 
					insuranceService.find(insuranceToPersist.getId()), priceList, insurance);
			if(!successfulConnection){
				logger.error("Sport price determination unsuccessful.");
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
		
		List<HumanAgeDTO> humanAges = travel.getHuman_age();
		if(!humanAges.isEmpty()){
			logger.info("Computing age price...");
			for(HumanAgeDTO age : humanAges){
				logger.info("Computing price for age category...");
				boolean successfulConnection = connectRiskItem(age.getId(), 
						insuranceService.find(insuranceToPersist.getId()), priceList, insurance);
				if(!successfulConnection){
					logger.error("Age price determination unsuccessful.");
					return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
				}
			}
		}
		
		if(insurance.getHome() != null){ //ako je kupac zeleo i osiguranje za kucu
			logger.info("Computing home insurance price...");
			HomeDTO homeDTO = insurance.getHome();
			Home home = new Home(homeDTO.getDuration(), homeDTO.getStart_date(),
					homeDTO.getEnd_date(), homeDTO.getFloor_area(), homeDTO.getFlat_age(), homeDTO.getEst_value());
			OwnerDTO ownerDTO = homeDTO.getOwner();
			if(ownerDTO.getFirst_name() == "" || ownerDTO.getFirst_name() == null ||
	        		ownerDTO.getLast_name() == "" || ownerDTO.getLast_name() == null
	        		|| ownerDTO.getJmbg() == null || ownerDTO.getJmbg() == ""){
				logger.error("Enter all the required fields of owner of home.");
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	        }
			BigDecimal homePrice = determineHomePrice(homeDTO);
			totalPrice = totalPrice.add(homePrice);
			List<Integer> casualties = homeDTO.getCasualty_ids();
			if(!casualties.isEmpty()){
				logger.info("Computing causes of taking home insurance...");
				for(Integer casualtyId : casualties){
					logger.info("Computing price for cause...");
					boolean successfulConnection = connectRiskItem(casualtyId, 
							insuranceService.find(insuranceToPersist.getId()), priceList, insurance);
					if(!successfulConnection){
						logger.error("Cause price determination unsuccessful.");
						return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
					}
				}
			}
			
			Owner ownerToPersist = new Owner(ownerDTO.getFirst_name(), ownerDTO.getLast_name(), ownerDTO.getJmbg());
			ownerService.save(ownerToPersist);
			home.setOwner(ownerToPersist);
			homeService.save(home);
			RiskTypeHome homeRiskType = new RiskTypeHome(riskTypeService.find(1), home); //riskType broj 1 u bazi je home
			riskTypeHomeService.save(homeRiskType);
		}
		
		if(insurance.getVehicle() != null){ //ako je kupac zeleo i osiguranje za vozilo
			VehicleDTO vehicleDTO = insurance.getVehicle();
			CarDTO carDTO = vehicleDTO.getCar();
			Vehicle vehicle = new Vehicle(vehicleDTO.getDuration(), vehicleDTO.getStart_date(), vehicleDTO.getEnd_date(), 
					carDTO.getType(), carDTO.getMan_year(), carDTO.getReg_num(), carDTO.getChassis_num());
			OwnerDTO ownerCarDTO = vehicleDTO.getOwner();
			if(ownerCarDTO.getFirst_name() == "" || ownerCarDTO.getFirst_name() == null ||
	        		ownerCarDTO.getLast_name() == "" || ownerCarDTO.getLast_name() == null
	        		|| ownerCarDTO.getJmbg() == null || ownerCarDTO.getJmbg() == ""){
				logger.error("Enter all the required fields of owner of home.");
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	        }
			
			BigDecimal vehiclePrice = determineVehiclePrice(vehicleDTO);
			totalPrice = totalPrice.add(vehiclePrice);
			
			Owner ownerCarToPersist = new Owner(ownerCarDTO.getFirst_name(), ownerCarDTO.getLast_name(), ownerCarDTO.getJmbg());
			ownerService.save(ownerCarToPersist);
			vehicle.setOwner(ownerCarToPersist);
			vehicleService.save(vehicle);
			RiskTypeVehicle vehicleRiskType = new RiskTypeVehicle(riskTypeService.find(2), vehicle); //riskType broj 1 u bazi je home
			riskTypeVehicleService.save(vehicleRiskType);
			
			//RIZICI
			//dodatak za slepanje vozila
			if (vehicleDTO.getTowing_id() != null) {
				logger.info("Computing towing price...");
				boolean successfulConnection = connectRiskItem(vehicleDTO.getTowing_id(), 
						insuranceService.find(insuranceToPersist.getId()), priceList, insurance);
				if(!successfulConnection){
					logger.error("Towing price determination unsuccessful.");
					return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
				}
			}
			
			//dodatak za popravku vozila
			if (vehicleDTO.getRepair_id() != null) {
				logger.info("Computing repair price...");
				boolean successfulConnection = connectRiskItem(vehicleDTO.getRepair_id(), 
						insuranceService.find(insuranceToPersist.getId()), priceList, insurance);
				if(!successfulConnection){
					logger.error("Repair price determination unsuccessful.");
					return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
				}
			}
			
			//dodatak za smestaj (vozilo)
			if (vehicleDTO.getAccomodation_id() != null) {
				logger.info("Computing accomodation price...");
				boolean successfulConnection = connectRiskItem(vehicleDTO.getAccomodation_id(), 
						insuranceService.find(insuranceToPersist.getId()), priceList, insurance);
				if(!successfulConnection){
					logger.error("Accomodation price determination unsuccessful.");
					return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
				}			}
			
			//dodatak za alternativni prevoz
			if (vehicleDTO.getAlternative_id() != null) {
				logger.info("Computing alternative car price...");
				boolean successfulConnection = connectRiskItem(vehicleDTO.getAlternative_id(), 
						insuranceService.find(insuranceToPersist.getId()), priceList, insurance);
				if(!successfulConnection){
					logger.error("Alternative car price determination unsuccessful.");
					return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
				}
			}
		}
		
		List<TravelerDTO> travellers = new ArrayList<TravelerDTO>();
		travellers = insurance.getTravellers();
		
		if(travellers.isEmpty())
			return null;
		
		logger.info("Computing travelers...");
		Traveler travelerToPersist;
		for(TravelerDTO travelerDTO : travellers){
			if(travelerDTO.getFirst_name() == "" || travelerDTO.getFirst_name() == null ||
	        		travelerDTO.getLast_name() == "" || travelerDTO.getLast_name() == null
	        		|| travelerDTO.getJmbg() == null || travelerDTO.getJmbg() == ""
	        		|| travelerDTO.getPassport_num() == null || travelerDTO.getPassport_num() == ""
	        		|| travelerDTO.getTel_num() == null || travelerDTO.getTel_num() == ""){
				logger.error("Enter all the required fields of traveler.");
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	        }
			travelerToPersist = new Traveler(travelerDTO.getFirst_name(), travelerDTO.getLast_name(), 
					travelerDTO.getPassport_num(), travelerDTO.getJmbg(), travelerDTO.getAddress(), 
					travelerDTO.getTel_num());
			travelerService.save(travelerToPersist);
			
			TravelerInsurance ti = new TravelerInsurance(travelerToPersist, insuranceToPersist);
			travelerInsuranceService.save(ti);
		}
		
		Set<PriceListItem> priceItems = new HashSet<PriceListItem>();
		priceItems = insuranceService.find(insuranceToPersist.getId()).getPriceListItems();
		for(PriceListItem item : priceItems){
			if(item.getRiskItem().getRisk().getRiskName().equals("age"))
				totalPrice = totalPrice.add(item.getPrice()); //ako su godine, samo se dodaje, a ako nisu mnozi se sa brojem ljudi
			else
				totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(travellers.size())));
		}
 
		java.util.Date currentDate = new Date();
		java.sql.Date merchantTimestamp = new java.sql.Date(currentDate.getTime());
		Order order = new Order(new Double(totalPrice.doubleValue()), merchantTimestamp, "http://localhost:8000/error",
				"http://localhost:8000/success", "http://localhost:8000/fail");
		Merchant merchant = merchantService.find(1);
		order.setMerchant(merchant);
		order.setInsurance(insuranceToPersist);
		Order savedOrder = orderService.save(order);
		
		//redirekcija na confirm metodu
		/*RestTemplate temp = new RestTemplate();
        temp.setErrorHandler(new DefaultResponseErrorHandler(){
            protected boolean hasError(HttpStatus statusCode) {
                return false;
            }
        });*/

        ObjectMapper mapper = new ObjectMapper();
        if(savedOrder.getId() == null)
        	return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        
        HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/confirm/{id}").buildAndExpand(savedOrder.getId()).toUri());
		String redirectUrl = "http://localhost:8080/spring4/confirm/{" + savedOrder.getId() + "}";
        return new ResponseEntity<String>(redirectUrl, headers, HttpStatus.CREATED);
        } catch (Exception e){
        	logger.info(e.toString());
        	return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	public boolean connectRiskItem(Integer riskItemId, Insurance insurance, PriceList priceList, 
			WholeInsuranceDTO wholeInsurance){
		try{
			RiskItem riskItem = riskItemService.find(riskItemId);
			if(riskItem == null)
				return false;
			RiskItemInsurance connectionSport = new RiskItemInsurance(riskItem,
					insurance);
			//Cena za pojedinacni riskItem
			BigDecimal price = PriceUtil.determineBasicPrice(wholeInsurance);
			price = price.multiply(new BigDecimal(riskItem.getFactor()));
			PriceListItem priceListItemSport = new PriceListItem(
					price, riskItem, priceList, insurance);
			priceListItemService.save(priceListItemSport);
			riskItemInsuranceService.save(connectionSport);
		} catch (Exception e){
			logger.error(e.toString());
			return false;
		}
		return true;
	}
	
	public BigDecimal determineRiskItemPrice(Integer riskItemId, BigDecimal basicPrice){
		try{
			BigDecimal riskPrice = basicPrice;
			RiskItem riskItem = riskItemService.find(riskItemId);
			if(riskItem == null || basicPrice.equals(BigDecimal.ZERO))
				return new BigDecimal(-1);
			riskPrice = riskPrice.multiply(new BigDecimal(riskItem.getFactor()));
			return riskPrice;
		} catch (Exception e){
			logger.error(e.toString());
			return new BigDecimal(-1);
		}
	}
	
	public BigDecimal determineHomePrice(HomeDTO home){
		BigDecimal homePrice = new BigDecimal(1.2); //1.2 EUR je cena osiguranja kuce po danu
		
		if(home.getDuration() == "" || home.getDuration() == null){
			if(home.getStart_date() == null){
				logger.error("Duration not set, as well as the start date for the home insurance.");
				return new BigDecimal(-1);
			} else if(home.getEnd_date() == null){
				logger.error("Duration not set, as well as the end date for the home insurance.");
				return new BigDecimal(-1);
			}
			long diff = home.getEnd_date().getTime() - home.getStart_date().getTime();
		    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		    homePrice = homePrice.multiply(new BigDecimal(days));
		} else 
			homePrice = homePrice.multiply(new BigDecimal(home.getDuration()));
		
		BigDecimal floorAreaFactor = homePrice.multiply(new BigDecimal(home.getFloor_area()/100)); //povrsina kuce povecava cenu
		BigDecimal flatAgeFactor = new BigDecimal(0);
		BigDecimal estValueFactor = homePrice.multiply(new BigDecimal(home.getFloor_area()/100000)); //procenjena cena kuce povecava cenu
		homePrice.add(floorAreaFactor);
		homePrice.add(estValueFactor);
		
		//RiskUtil riskUtil = new RiskUtil();
		if(home.getCasualty_ids().size() != 0 || home.getCasualty_ids() != null){
			for(Integer casualtyId : home.getCasualty_ids()){
				if(!determineRiskItemPrice(casualtyId, homePrice).equals(new BigDecimal(-1)))
					homePrice = homePrice.add(determineRiskItemPrice(casualtyId, homePrice));
				else
					return new BigDecimal(-1);
			}
		}
		return homePrice;
	}
	
	public BigDecimal determineVehiclePrice(VehicleDTO vehicle){
		BigDecimal vehiclePrice = new BigDecimal(1.1); //1.1 EUR je cena osiguranja kuce po danu
		
		if(vehicle.getDuration() == "" || vehicle.getDuration() == null){
			if(vehicle.getStart_date() == null){
				logger.error("Duration not set, as well as the start date for the vehicle insurance.");
				return new BigDecimal(-1);
			} else if(vehicle.getEnd_date() == null){
				logger.error("Duration not set, as well as the end date for the vehicle insurance.");
				return new BigDecimal(-1);
			}
			long diff = vehicle.getEnd_date().getTime() - vehicle.getStart_date().getTime();
		    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		    vehiclePrice = vehiclePrice.multiply(new BigDecimal(days));
		} else 
			vehiclePrice = vehiclePrice.multiply(new BigDecimal(vehicle.getDuration()));
		
		//RiskUtil riskUtil = new RiskUtil();
		if(vehicle.getTowing_id() != null){
			if(!determineRiskItemPrice(vehicle.getTowing_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(determineRiskItemPrice(vehicle.getTowing_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}
		if(vehicle.getAccomodation_id() != null){
			if(!determineRiskItemPrice(vehicle.getAccomodation_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(determineRiskItemPrice(vehicle.getAccomodation_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}
		if(vehicle.getAlternative_id() != null){
			if(!determineRiskItemPrice(vehicle.getAlternative_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(determineRiskItemPrice(vehicle.getAlternative_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}	
		if(vehicle.getRepair_id() != null){
			if(!determineRiskItemPrice(vehicle.getRepair_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(determineRiskItemPrice(vehicle.getRepair_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}	
		
		return vehiclePrice;
	}
}
