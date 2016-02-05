package org.sep.merchant.form.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "risk_type")
public class RiskType implements Serializable{

	private static final long serialVersionUID = -5516686705421764670L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_risk_type")
	private Integer id;
	
	@Column (name = "name_risk_type", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "type", cascade = {CascadeType.ALL})  
	private Set<Risk> risks = new HashSet<Risk>();
	
	@OneToMany(mappedBy = "type")  
	private Set<RiskTypeInfo> riskTypeInfos = new HashSet<RiskTypeInfo>();
	
	/*@ManyToMany(fetch = FetchType.LAZY, mappedBy = "riskTypes")
	private Set<Home> homes = new HashSet<Home>();
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "riskTypes")
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();*/
	
	@OneToMany(mappedBy = "riskType")  
	private Set<RiskTypeHome> riskTypeHomes = new HashSet<RiskTypeHome>();
	
	@OneToMany(mappedBy = "riskType")  
	private Set<RiskTypeVehicle> riskTypeVehicles = new HashSet<RiskTypeVehicle>();
	
	public RiskType(){
		super();
	}

	public RiskType(String name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Risk> getRisks() {
		return risks;
	}

	public void setRisks(Set<Risk> risks) {
		this.risks = risks;
	}

	public Set<RiskTypeInfo> getRiskInfos() {
		return riskTypeInfos;
	}

	public void setRiskInfos(Set<RiskTypeInfo> riskTypeInfos) {
		this.riskTypeInfos = riskTypeInfos;
	}

	public Set<RiskTypeInfo> getRiskTypeInfos() {
		return riskTypeInfos;
	}

	public void setRiskTypeInfos(Set<RiskTypeInfo> riskTypeInfos) {
		this.riskTypeInfos = riskTypeInfos;
	}

}
