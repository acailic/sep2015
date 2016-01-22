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
@Table(name = "risk_item_insurance")
public class RiskItemInsurance implements Serializable{

	private static final long serialVersionUID = 3449085086550596522L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_risk_item_insurance")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_risk_item")
	private RiskItem riskItem;
	
	@ManyToOne
	@JoinColumn(name="id_insurance")
	private Insurance insurance;

	public RiskItemInsurance(Integer id, RiskItem riskItem, Insurance insurance) {
		super();
		this.id = id;
		this.riskItem = riskItem;
		this.insurance = insurance;
	}

	public RiskItem getRiskItem() {
		return riskItem;
	}

	public void setRiskItem(RiskItem riskItem) {
		this.riskItem = riskItem;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

}
