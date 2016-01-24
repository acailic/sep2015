package org.sep.merchant.form.controllers;

import org.sep.merchant.form.model.Risk;
import org.sep.merchant.form.model.RiskItem;
import org.sep.merchant.form.model.RiskType;
import org.sep.merchant.form.model.Traveler;
import org.sep.merchant.form.service.RiskItemService;
import org.sep.merchant.form.service.RiskService;
import org.sep.merchant.form.service.RiskTypeService;
import org.sep.merchant.form.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RiskTypeController {
	
	@Autowired
	RiskTypeService riskTypeService;
	
	@Autowired
	RiskService riskService;
	
	@Autowired
	RiskItemService riskItemService;
	
	@RequestMapping(value = "/data_init", method = RequestMethod.GET)
    public ResponseEntity<String> populateData() {
		String response = "OK";
        
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
        
        
        System.out.println("Usao u RiskTypeController");
        return new ResponseEntity<String>(response, HeaderUtil.getHeader(), HttpStatus.OK);
    }

}
