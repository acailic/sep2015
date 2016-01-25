package org.sep.merchant.form.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class HomeDTO implements Serializable{

	private static final long serialVersionUID = 1567716752355104708L;
	
	private String duration;
	private Date start_date;
	private Date end_date;
	private Double floor_area;
	private Integer flat_age;
	private BigDecimal est_value;
	private Integer casualty_id;
	private OwnerDTO owner;
	
	public HomeDTO(){
		
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

	public Double getFloor_area() {
		return floor_area;
	}

	public void setFloor_area(Double floor_area) {
		this.floor_area = floor_area;
	}

	public Integer getFlat_age() {
		return flat_age;
	}

	public void setFlat_age(Integer flat_age) {
		this.flat_age = flat_age;
	}

	public BigDecimal getEst_value() {
		return est_value;
	}

	public void setEst_value(BigDecimal est_value) {
		this.est_value = est_value;
	}

	public Integer getCasualty_id() {
		return casualty_id;
	}

	public void setCasualty_id(Integer casualty_id) {
		this.casualty_id = casualty_id;
	}

	public OwnerDTO getOwner() {
		return owner;
	}

	public void setOwner(OwnerDTO owner) {
		this.owner = owner;
	}
}
