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
@Table(name = "risk_type_vehicle")
public class RiskTypeVehicle implements Serializable{
	
	private static final long serialVersionUID = -6253511600153524291L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_risk_type_vehicle")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="id_risk_type")
	private RiskType riskType;
	
	@ManyToOne
	@JoinColumn(name="id_vehicle")
	private Vehicle vehicle;

	public RiskTypeVehicle(Integer id, RiskType riskType, Vehicle vehicle) {
		super();
		this.id = id;
		this.riskType = riskType;
		this.vehicle = vehicle;
	}

	public RiskType getRiskType() {
		return riskType;
	}

	public void setRiskType(RiskType riskType) {
		this.riskType = riskType;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	

}
