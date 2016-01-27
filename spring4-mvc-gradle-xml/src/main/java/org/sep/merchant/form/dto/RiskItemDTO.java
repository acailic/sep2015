package org.sep.merchant.form.dto;

import java.io.Serializable;

public class RiskItemDTO implements Serializable{
	
	private static final long serialVersionUID = -2085622083239076003L;
	
	private Integer id;
	private String name;
	
	public RiskItemDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public RiskItemDTO(){
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	

}
