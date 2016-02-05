package org.sep.merchant.form.util;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.sep.merchant.form.dto.HomeDTO;
import org.sep.merchant.form.dto.VehicleDTO;
import org.sep.merchant.form.dto.WholeInsuranceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriceUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(PriceUtil.class);
	
	//multiplikuje se sve osim age rizika, on se dodaje samo na kraju
	public static BigDecimal determineBasicPrice(WholeInsuranceDTO insurance){
		BigDecimal basicPrice = new BigDecimal(1); //1EUR je cena osiguranja po danu
		
		if(insurance.getTravel().getDuration() == "" || insurance.getTravel().getDuration() == null){
			if(insurance.getTravel().getStart_date() == null){
				logger.error("Duration not set, as well as the start date.");
				return new BigDecimal(-1);
			} else if(insurance.getTravel().getEnd_date() == null){
				logger.error("Duration not set, as well as the end date.");
				return new BigDecimal(-1);
			}
			long diff = insurance.getTravel().getEnd_date().getTime() - insurance.getTravel().getStart_date().getTime();
		    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		    basicPrice = basicPrice.multiply(new BigDecimal(days));
		} else 
			basicPrice = basicPrice.multiply(new BigDecimal(insurance.getTravel().getDuration())); //mnozi se sa brojem dana
			//basicPrice = basicPrice.multiply(new BigDecimal(insurance.getTravellers().size())); //mnozenje s brojem ljudi
		
			return basicPrice;
	}

}
