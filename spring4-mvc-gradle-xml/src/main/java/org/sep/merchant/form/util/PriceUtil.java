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
		    //basicPrice = basicPrice.multiply(new BigDecimal(insurance.getTravellers().size()));
		} else 
			basicPrice = basicPrice.multiply(new BigDecimal(insurance.getTravel().getDuration())); //mnozi se sa brojem dana
			//basicPrice = basicPrice.multiply(new BigDecimal(insurance.getTravellers().size())); //mnozenje s brojem ljudi
		
			return basicPrice;
	}
	
	/*public static BigDecimal determineHomePrice(HomeDTO home){
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
		
		RiskUtil riskUtil = new RiskUtil();
		if(home.getCasualty_ids().size() != 0 || !home.getCasualty_ids().equals(null)){
			for(Integer casualtyId : home.getCasualty_ids()){
				if(!riskUtil.determineRiskItemPrice(casualtyId, homePrice).equals(new BigDecimal(-1)))
					homePrice = homePrice.add(riskUtil.determineRiskItemPrice(casualtyId, homePrice));
				else
					return new BigDecimal(-1);
			}
		}
		return homePrice;
	}
	
	public static BigDecimal determineVehiclePrice(VehicleDTO vehicle){
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
		
		RiskUtil riskUtil = new RiskUtil();
		if(!vehicle.getTowing_id().equals(null)){
			if(!riskUtil.determineRiskItemPrice(vehicle.getTowing_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(riskUtil.determineRiskItemPrice(vehicle.getTowing_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}
		if(!vehicle.getAccomodation_id().equals(null)){
			if(!riskUtil.determineRiskItemPrice(vehicle.getAccomodation_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(riskUtil.determineRiskItemPrice(vehicle.getAccomodation_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}
		if(!vehicle.getAlternative_id().equals(null)){
			if(!riskUtil.determineRiskItemPrice(vehicle.getAlternative_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(riskUtil.determineRiskItemPrice(vehicle.getAlternative_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}	
		if(!vehicle.getRepair_id().equals(null)){
			if(!riskUtil.determineRiskItemPrice(vehicle.getRepair_id(), vehiclePrice).equals(new BigDecimal(-1)))
				vehiclePrice = vehiclePrice.add(riskUtil.determineRiskItemPrice(vehicle.getRepair_id(), vehiclePrice));
			else
				return new BigDecimal(-1);
		}	
		
		return vehiclePrice;
	}*/

}
