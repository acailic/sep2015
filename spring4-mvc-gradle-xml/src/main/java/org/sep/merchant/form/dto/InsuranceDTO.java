package org.sep.merchant.form.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class InsuranceDTO implements Serializable{
	
	private static final long serialVersionUID = 3592561497808553807L;
	
	private String duration;
	private Date start_date;
	private Date end_date;
	private Integer region_id; //RiskItem ID
	private List<HumanAgeDTO> human_age = new ArrayList<HumanAgeDTO>();
	private Integer max_value_id; //RiskItem ID
	private Integer sport_id; //RiskItem ID
	private OwnerDTO owner;
	
	public InsuranceDTO(){
		
	}
	
	public InsuranceDTO(String duration, Date start_date, Date end_date,
			Integer region_id, List<HumanAgeDTO> human_age, Integer max_value_id,
			Integer sport_id, OwnerDTO owner) {
		super();
		this.duration = duration;
		this.start_date = start_date;
		this.end_date = end_date;
		this.region_id = region_id;
		this.human_age = human_age;
		this.max_value_id = max_value_id;
		this.sport_id = sport_id;
		this.owner = owner;
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

	public Integer getRegion_id() {
		return region_id;
	}

	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}

	public List<HumanAgeDTO> getHuman_age() {
		return human_age;
	}

	public void setHuman_age(List<HumanAgeDTO> human_age) {
		this.human_age = human_age;
	}

	public Integer getMax_value_id() {
		return max_value_id;
	}

	public void setMax_value_id(Integer max_value_id) {
		this.max_value_id = max_value_id;
	}

	public Integer getSport_id() {
		return sport_id;
	}

	public void setSport_id(Integer sport_id) {
		this.sport_id = sport_id;
	}
	
	public OwnerDTO getOwner() {
		return owner;
	}
	
	public void setOwner(OwnerDTO owner) {
		this.owner = owner;
	}
}
