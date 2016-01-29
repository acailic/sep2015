package org.sep.merchant.form.util;

import java.math.BigDecimal;

import org.sep.merchant.form.model.Insurance;
import org.sep.merchant.form.model.PriceList;
import org.sep.merchant.form.model.PriceListItem;
import org.sep.merchant.form.model.RiskItem;
import org.sep.merchant.form.model.RiskItemInsurance;
import org.sep.merchant.form.service.PriceListItemService;
import org.sep.merchant.form.service.RiskItemInsuranceService;
import org.sep.merchant.form.service.RiskItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RiskUtil {
	
	private final Logger logger = LoggerFactory.getLogger(RiskUtil.class);
	
	@Autowired
	RiskItemService riskItemService;
	
	@Autowired
	RiskItemInsuranceService riskItemInsuranceService;
	
	@Autowired
	PriceListItemService priceListItemService;
	
	public boolean connectRiskItem(Integer riskItemId, Insurance insurance, PriceList priceList){
		try{
			RiskItem riskItem = riskItemService.find(riskItemId);
			if(riskItem == null)
				return false;
			RiskItemInsurance connectionSport = new RiskItemInsurance(riskItem,
					insurance);
			// CENA +++++
			PriceListItem priceListItemSport = new PriceListItem(
					new BigDecimal(0), riskItem, priceList, insurance);
			priceListItemService.save(priceListItemSport);
			riskItemInsuranceService.save(connectionSport);
		} catch (Exception e){
			logger.error(e.toString());
			return false;
		}
		return true;
	}

}
