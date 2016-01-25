package org.sep.merchant.form.dto;

import java.io.Serializable;

public class HumanAgeDTO implements Serializable{
	
	private static final long serialVersionUID = -7240965998941418522L;
	
	private Integer id;
	private String value;
	private Integer number_of_people;
	
	public HumanAgeDTO(){
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public Integer getNumber_of_people() {
		return number_of_people;
	}

	public void setNumber_of_people(Integer number_of_people) {
		this.number_of_people = number_of_people;
	}
	

}
