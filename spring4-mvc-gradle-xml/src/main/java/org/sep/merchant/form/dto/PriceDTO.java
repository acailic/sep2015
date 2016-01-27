package org.sep.merchant.form.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PriceDTO implements Serializable{

	private static final long serialVersionUID = 2511866326268502623L;
	
	private BigDecimal travel_price;
	private BigDecimal home_price;
	private BigDecimal vehicle_price;
	
	public PriceDTO(BigDecimal travel_price, BigDecimal home_price,
			BigDecimal vehicle_price) {
		super();
		this.travel_price = travel_price;
		this.home_price = home_price;
		this.vehicle_price = vehicle_price;
	}

	public PriceDTO(){
		
	}

	public BigDecimal getTravel_price() {
		return travel_price;
	}

	public void setTravel_price(BigDecimal travel_price) {
		this.travel_price = travel_price;
	}

	public BigDecimal getHome_price() {
		return home_price;
	}

	public void setHome_price(BigDecimal home_price) {
		this.home_price = home_price;
	}

	public BigDecimal getVehicle_price() {
		return vehicle_price;
	}

	public void setVehicle_price(BigDecimal vehicle_price) {
		this.vehicle_price = vehicle_price;
	}

}
