package org.sep.merchant.form.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WholeInsuranceDTO implements Serializable{

	private static final long serialVersionUID = 315386558057687311L;
	
	private InsuranceDTO travel;
	private HomeDTO home;
	private VehicleDTO vehicle;
	private List<TravelerDTO> travellers = new ArrayList<TravelerDTO>();
	
	public WholeInsuranceDTO(InsuranceDTO travel, HomeDTO home,
			VehicleDTO vehicle, List<TravelerDTO> travellers) {
		super();
		this.travel = travel;
		this.home = home;
		this.vehicle = vehicle;
		this.travellers = travellers;
	}

	public WholeInsuranceDTO(){
		
	}

	public InsuranceDTO getTravel() {
		return travel;
	}

	public void setTravel(InsuranceDTO travel) {
		this.travel = travel;
	}

	public HomeDTO getHome() {
		return home;
	}

	public void setHome(HomeDTO home) {
		this.home = home;
	}

	public VehicleDTO getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleDTO vehicle) {
		this.vehicle = vehicle;
	}

	public List<TravelerDTO> getTravellers() {
		return travellers;
	}

	public void setTravelers(List<TravelerDTO> travellers) {
		this.travellers = travellers;
	}

	
}
