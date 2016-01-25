package org.sep.merchant.form.dto;

import java.io.Serializable;
import java.util.List;

public class DataInitDTO implements Serializable{
	
	private static final long serialVersionUID = 5995453269008556919L;
	
	private List<RiskItemDTO> regions;
	private List<RiskItemDTO> max_values;
	private List<RiskItemDTO> sports;
	private List<RiskItemDTO> casualties;
	private List<RiskItemDTO> towing;
	private List<RiskItemDTO> repair;
	private List<RiskItemDTO> accomodation;
	private List<RiskItemDTO> alternative;
	private List<RiskItemDTO> brands;
	private List<RiskItemDTO> cities;
	
	public DataInitDTO(){
		
	}
	
	public List<RiskItemDTO> getRegions() {
		return regions;
	}
	public void setRegions(List<RiskItemDTO> regions) {
		this.regions = regions;
	}
	public List<RiskItemDTO> getMax_values() {
		return max_values;
	}
	public void setMax_values(List<RiskItemDTO> max_values) {
		this.max_values = max_values;
	}
	public List<RiskItemDTO> getSports() {
		return sports;
	}
	public void setSports(List<RiskItemDTO> sports) {
		this.sports = sports;
	}
	public List<RiskItemDTO> getCasualties() {
		return casualties;
	}
	public void setCasualties(List<RiskItemDTO> casualties) {
		this.casualties = casualties;
	}
	public List<RiskItemDTO> getTowing() {
		return towing;
	}
	public void setTowing(List<RiskItemDTO> towing) {
		this.towing = towing;
	}
	public List<RiskItemDTO> getRepair() {
		return repair;
	}
	public void setRepair(List<RiskItemDTO> repair) {
		this.repair = repair;
	}
	public List<RiskItemDTO> getAccomodation() {
		return accomodation;
	}
	public void setAccomodation(List<RiskItemDTO> accomodation) {
		this.accomodation = accomodation;
	}
	public List<RiskItemDTO> getAlternative() {
		return alternative;
	}
	public void setAlternative(List<RiskItemDTO> alternative) {
		this.alternative = alternative;
	}
	public List<RiskItemDTO> getBrands() {
		return brands;
	}
	public void setBrands(List<RiskItemDTO> brands) {
		this.brands = brands;
	}
	public List<RiskItemDTO> getCities() {
		return cities;
	}
	public void setCities(List<RiskItemDTO> cities) {
		this.cities = cities;
	}
	
	

}
