package org.sep.merchant.form.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "risk_type_home")
public class RiskTypeHome implements Serializable{

	private static final long serialVersionUID = 7831041690331904533L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_risk_type_home")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_risk_type")
	private RiskType riskType;
	
	@ManyToOne
	@JoinColumn(name="id_home")
	private Home home;
	
	public RiskTypeHome(){
		
	}

	public RiskTypeHome(RiskType riskType, Home home) {
		super();
		this.riskType = riskType;
		this.home = home;
	}

	public RiskType getRiskType() {
		return riskType;
	}

	public void setRiskType(RiskType riskType) {
		this.riskType = riskType;
	}

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

}
