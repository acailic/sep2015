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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "risk_item")
public class RiskItem implements Serializable{

	private static final long serialVersionUID = 8075718661754422574L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_risk_item")
	private Integer id;
	
	@Column (name = "name_risk_item", nullable = false)
	private String name;
	
	@Column (name = "factor_risk_item", nullable = false)
	private Double factor;
	
	@ManyToOne
	@JoinColumn(name="id_risk")
	private Risk risk;
	
	@OneToMany(mappedBy = "riskItem")  
	private Set<PriceListItem> priceListItems = new HashSet<PriceListItem>();
	
	/*@ManyToMany(fetch = FetchType.LAZY, mappedBy = "riskItems")
	private Set<Insurance> insurances = new HashSet<Insurance>();*/
	
	@OneToMany(mappedBy = "riskItem")  
	private Set<RiskItemInsurance> riskItemInsurances = new HashSet<RiskItemInsurance>();

	public RiskItem(Integer id, String name, Double factor, Risk risk,
			Set<Insurance> insurances) {
		super();
		this.id = id;
		this.name = name;
		this.factor = factor;
		this.risk = risk;
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

	public Double getFactor() {
		return factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
	}

	public Set<PriceListItem> getPriceListItems() {
		return priceListItems;
	}

	public void setPriceListItems(Set<PriceListItem> priceListItems) {
		this.priceListItems = priceListItems;
	}
	
	
}
