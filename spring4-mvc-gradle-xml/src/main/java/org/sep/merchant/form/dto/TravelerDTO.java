package org.sep.merchant.form.dto;

import java.io.Serializable;

public class TravelerDTO implements Serializable{

	private static final long serialVersionUID = 6093499226629341387L;
	
	private String jmbg;
	private String passport_num;
	private String tel_num;
	private String first_name;
	private String last_name;
	private Integer human_age_id;
	private Integer city_id;
	private String address;
	
	public TravelerDTO(){
		
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getPassport_num() {
		return passport_num;
	}

	public void setPassport_num(String passport_num) {
		this.passport_num = passport_num;
	}

	public String getTel_num() {
		return tel_num;
	}

	public void setTel_num(String tel_num) {
		this.tel_num = tel_num;
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

	public Integer getHuman_age_id() {
		return human_age_id;
	}

	public void setHuman_age_id(Integer human_age_id) {
		this.human_age_id = human_age_id;
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
