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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "risk")
public class Risk implements Serializable{

	private static final long serialVersionUID = -1458768936693093185L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_risk")
	private Integer id;
	
	@Column (name = "name_risk", nullable = false)
	private String riskName;
	
	@ManyToOne
	@JoinColumn(name="id_risk_type")
	private RiskType type;
	
	@OneToMany(mappedBy = "risk", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)  
	private Set<RiskItem> riskItems = new HashSet<RiskItem>();
	
	public Risk(){
		
	}

	public Risk(String riskName, RiskType type) {
		super();
		this.riskName = riskName;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public RiskType getType() {
		return type;
	}

	public void setType(RiskType type) {
		this.type = type;
	}

	public Set<RiskItem> getRiskItems() {
		return riskItems;
	}

	public void setRiskItems(Set<RiskItem> riskItems) {
		this.riskItems = riskItems;
	}
	
}
