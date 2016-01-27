package org.sep.merchant.form.controllers;

import java.util.ArrayList;
import java.util.List;

import org.sep.merchant.form.dto.DataInitDTO;
import org.sep.merchant.form.dto.RiskItemDTO;
import org.sep.merchant.form.service.RiskItemService;
import org.sep.merchant.form.service.RiskService;
import org.sep.merchant.form.service.RiskTypeService;
import org.sep.merchant.form.util.DTOConversion;
import org.sep.merchant.form.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RiskTypeController {
	
	private final Logger logger = LoggerFactory.getLogger(RiskTypeController.class);
	
	@Autowired
	RiskTypeService riskTypeService;
	
	@Autowired
	RiskService riskService;
	
	@Autowired
	RiskItemService riskItemService;
	
	@RequestMapping(value = "/data_init", method = RequestMethod.GET)
    public ResponseEntity<?> populateData() {
		
		logger.info("Populating data in populateData method.");
		
		DataInitDTO data_init = new DataInitDTO();
		/*List<Risk> allRisks = new ArrayList<Risk>();
		allRisks = riskService.findAll(); *///da li ih odmah uzeti sve iz baze, ili jedno po jedno se obracati servisu?
		
		try {
			List<RiskItemDTO> regions = new ArrayList<RiskItemDTO>();
			regions = DTOConversion.getRiskItemDTOs(riskService.find(2)
					.getRiskItems()); // region ima u bazi ID 2
			data_init.setRegions(regions);

			List<RiskItemDTO> max_values = new ArrayList<RiskItemDTO>();
			max_values = DTOConversion.getRiskItemDTOs(riskService.find(3)
					.getRiskItems()); // max_value ima u bazi ID 3
			data_init.setMax_values(max_values);

			List<RiskItemDTO> sports = new ArrayList<RiskItemDTO>();
			sports = DTOConversion.getRiskItemDTOs(riskService.find(1)
					.getRiskItems()); // sport ima u bazi ID 2
			data_init.setSports(sports);

			// List<RiskItemDTO> casualties = new ArrayList<RiskItemDTO>();

			List<RiskItemDTO> towing = new ArrayList<RiskItemDTO>();
			towing = DTOConversion.getRiskItemDTOs(riskService.find(4)
					.getRiskItems());
			data_init.setTowing(towing);

			List<RiskItemDTO> repair = new ArrayList<RiskItemDTO>();
			repair = DTOConversion.getRiskItemDTOs(riskService.find(5)
					.getRiskItems());
			data_init.setRepair(repair);
			
			logger.info("Data populated.");
			return new ResponseEntity<DataInitDTO>(data_init, HeaderUtil.getHeader(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		/*List<RiskItemDTO> accomodation = new ArrayList<RiskItemDTO>();
		List<RiskItemDTO> alternative = new ArrayList<RiskItemDTO>();
		List<RiskItemDTO> brands = new ArrayList<RiskItemDTO>();
		List<RiskItemDTO> cities = new ArrayList<RiskItemDTO>();
	
		//popunjavanje baze
		
        RiskType homeRiskType = new RiskType("home");
        RiskType vehicleRiskType = new RiskType("vehicle"); //ili package?
        RiskType travelRiskType = new RiskType("travel");
        riskTypeService.save(homeRiskType);
        riskTypeService.save(vehicleRiskType);
        riskTypeService.save(travelRiskType);
        
        Risk sportRisk = new Risk("sport", travelRiskType);
        RiskItem hockeyRI = new RiskItem("hokej", 0.3, sportRisk);
        RiskItem footballRI = new RiskItem("fudbal", 0.1, sportRisk);
        RiskItem basketballRI = new RiskItem("kosarka", 0.2, sportRisk);
        riskService.save(sportRisk);
        riskItemService.save(hockeyRI);
        riskItemService.save(footballRI);
        riskItemService.save(basketballRI);
        
        Risk regionRisk = new Risk("region", travelRiskType);
        riskService.save(regionRisk);
        RiskItem europeRI = new RiskItem("Evropa", 0.3, regionRisk);
        RiskItem asiaRI = new RiskItem("Azija", 0.1, regionRisk);
        RiskItem americaRI = new RiskItem("Severna Amerika", 0.2, regionRisk);
        riskItemService.save(europeRI);
        riskItemService.save(asiaRI);
        riskItemService.save(americaRI);
        
        Risk maxValueRisk = new Risk("max_value", travelRiskType);
        riskService.save(maxValueRisk);
        RiskItem mv10RI = new RiskItem("10.000 EUR", 0.1, maxValueRisk); //max value 10000
        RiskItem mv20RI = new RiskItem("20.000 EUR", 0.2, maxValueRisk);
        RiskItem mv30RI = new RiskItem("30.000 EUR", 0.3, maxValueRisk);
        riskItemService.save(mv10RI);
        riskItemService.save(mv20RI);
        riskItemService.save(mv30RI);
        
        Risk casualtyRisk = new Risk("casualty", travelRiskType);
        
        Risk towingRisk = new Risk("towing", vehicleRiskType);
        riskService.save(towingRisk);
        RiskItem towing100RI = new RiskItem("100 km", 0.1, towingRisk); 
        RiskItem towing200RI = new RiskItem("200 km", 0.2, towingRisk);
        RiskItem towing300RI = new RiskItem("300 km", 0.3, towingRisk);
        riskItemService.save(towing100RI);
        riskItemService.save(towing200RI);
        riskItemService.save(towing300RI);
        
        Risk repairRisk = new Risk("repair", vehicleRiskType);
        riskService.save(repairRisk);
        RiskItem repair500RI = new RiskItem("500 EUR", 0.1, repairRisk); 
        RiskItem repair800RI = new RiskItem("800 EUR", 0.2, repairRisk);
        RiskItem repair1000RI = new RiskItem("1000 EUR", 0.3, repairRisk);
        riskItemService.save(repair500RI);
        riskItemService.save(repair800RI);
        riskItemService.save(repair1000RI);
        
        Risk brandRisk = new Risk("brand", vehicleRiskType);
        
        return new ResponseEntity<String>("ОК", HeaderUtil.getHeader(), HttpStatus.OK);*/
        
    }

}
