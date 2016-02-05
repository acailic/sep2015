package org.sep.merchant.form.util;

import java.math.BigDecimal;

import org.sep.merchant.form.dto.WholeInsuranceDTO;
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
	
	public RiskUtil(){
	}
	
	public BigDecimal determineRiskItemPrice(RiskItem riskItem, BigDecimal basicPrice){
		try{
			BigDecimal riskPrice = basicPrice;
			if(riskItem == null || basicPrice.equals(BigDecimal.ZERO))
				return new BigDecimal(-1);
			riskPrice = riskPrice.multiply(new BigDecimal(riskItem.getFactor()));
			return riskPrice;
		} catch (Exception e){
			logger.error(e.toString());
			return new BigDecimal(-1);
		}
	}
	
}
