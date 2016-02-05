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

			List<RiskItemDTO> casualties = new ArrayList<RiskItemDTO>();
			casualties = DTOConversion.getRiskItemDTOs(riskService.find(6)
					.getRiskItems()); // sport ima u bazi ID 2
			data_init.setCasualties(casualties);

			List<RiskItemDTO> towing = new ArrayList<RiskItemDTO>();
			towing = DTOConversion.getRiskItemDTOs(riskService.find(4)
					.getRiskItems());
			data_init.setTowing(towing);

			List<RiskItemDTO> repair = new ArrayList<RiskItemDTO>();
			repair = DTOConversion.getRiskItemDTOs(riskService.find(5)
					.getRiskItems());
			data_init.setRepair(repair);
			
			List<RiskItemDTO> cities = new ArrayList<RiskItemDTO>();
			cities = DTOConversion.getRiskItemDTOs(riskService.find(7)
					.getRiskItems());
			data_init.setCities(cities);
			
			List<RiskItemDTO> accomodation = new ArrayList<RiskItemDTO>();
			accomodation = DTOConversion.getRiskItemDTOs(riskService.find(9)
					.getRiskItems());
			data_init.setAccomodation(accomodation);
			
			List<RiskItemDTO> alternative = new ArrayList<RiskItemDTO>();
			alternative = DTOConversion.getRiskItemDTOs(riskService.find(10)
					.getRiskItems());
			data_init.setAlternative(alternative);
			
			List<RiskItemDTO> brands = new ArrayList<RiskItemDTO>();
			brands = DTOConversion.getRiskItemDTOs(riskService.find(8)
					.getRiskItems());
			data_init.setBrands(brands);
			
			List<RiskItemDTO> ages = new ArrayList<RiskItemDTO>();
			ages = DTOConversion.getRiskItemDTOs(riskService.find(11)
					.getRiskItems());
			data_init.setAges(ages);
			
			logger.info("Data populated.");
			return new ResponseEntity<DataInitDTO>(data_init, HeaderUtil.getHeader(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
    }

}
