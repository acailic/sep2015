package org.sep.merchant.form.dto;

import java.io.Serializable;

public class CarDTO implements Serializable{
	
	private static final long serialVersionUID = -8093778957285055562L;
	
	private Integer brand_id;
	private String type;
	private String man_year;
	private String reg_num;
	private String chassis_num;
	
	public CarDTO(){
		
	}

	public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMan_year() {
		return man_year;
	}

	public void setMan_year(String man_year) {
		this.man_year = man_year;
	}

	public String getReg_num() {
		return reg_num;
	}

	public void setReg_num(String reg_num) {
		this.reg_num = reg_num;
	}

	public String getChassis_num() {
		return chassis_num;
	}

	public void setChassis_num(String chassis_num) {
		this.chassis_num = chassis_num;
	}
}
