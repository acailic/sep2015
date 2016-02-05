package org.sep.merchant.form.dto;

import java.io.Serializable;

public class OwnerDTO implements Serializable{
	
	private static final long serialVersionUID = -3915420882283904248L;
	
	private String jmbg;
	private String first_name;
	private String last_name;
	private String email;
	private Integer city_id; //RiskItem ID
	private String address;
	
	public OwnerDTO(){
		
	}
	
	public OwnerDTO(String jmbg, String first_name, String last_name,
			String email, Integer city_id, String address) {
		super();
		this.jmbg = jmbg;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.city_id = city_id;
		this.address = address;
	}



	public String getJmbg() {
		return jmbg;
	}
	
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	

}
