package org.sep.merchant.form.dto;

import java.io.Serializable;
import java.sql.Date;

public class VehicleDTO implements Serializable{
	
	private static final long serialVersionUID = 1411749563464074398L;
	
	private String duration;
	private Date start_date;
	private Date end_date;
	private Integer towing_id;
	private Integer repair_id;
	private Integer accomodation_id;
	private Integer alternative_id;
	private CarDTO car;
	private OwnerDTO owner;
	
	public VehicleDTO(String duration, Date start_date, Date end_date,
			Integer towing_id, Integer repair_id, Integer accomodation_id,
			Integer alternative_id, CarDTO car, OwnerDTO owner) {
		super();
		this.duration = duration;
		this.start_date = start_date;
		this.end_date = end_date;
		this.towing_id = towing_id;
		this.repair_id = repair_id;
		this.accomodation_id = accomodation_id;
		this.alternative_id = alternative_id;
		this.car = car;
		this.owner = owner;
	}

	public VehicleDTO(){
		
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Integer getTowing_id() {
		return towing_id;
	}

	public void setTowing_id(Integer towing_id) {
		this.towing_id = towing_id;
	}

	public Integer getRepair_id() {
		return repair_id;
	}

	public void setRepair_id(Integer repair_id) {
		this.repair_id = repair_id;
	}

	public Integer getAccomodation_id() {
		return accomodation_id;
	}

	public void setAccomodation_id(Integer accomodation_id) {
		this.accomodation_id = accomodation_id;
	}

	public Integer getAlternative_id() {
		return alternative_id;
	}

	public void setAlternative_id(Integer alternative_id) {
		this.alternative_id = alternative_id;
	}

	public CarDTO getCar() {
		return car;
	}

	public void setCar(CarDTO car) {
		this.car = car;
	}

	public OwnerDTO getOwner() {
		return owner;
	}

	public void setOwner(OwnerDTO owner) {
		this.owner = owner;
	}
}
