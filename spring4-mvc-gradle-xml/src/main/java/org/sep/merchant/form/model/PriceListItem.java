package org.sep.merchant.form.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "price_list_item")
public class PriceListItem implements Serializable{

	private static final long serialVersionUID = -4603964035883421704L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_price_list_item")
	private Integer id;
	
	@Column (name = "price", nullable = false)
	private BigDecimal price;
	
	//@Id
	@ManyToOne
	@JoinColumn(name="id_risk_item")
	private RiskItem riskItem;
	
	@ManyToOne
	@JoinColumn(name="id_price_list")
	private PriceList priceList;
	
	@ManyToOne
	@JoinColumn(name="id_insurance")
	private Insurance insurance;
	
	public PriceListItem(){
		
	}

	public PriceListItem(BigDecimal price, RiskItem riskItem,
			PriceList priceList, Insurance insurance) {
		super();
		this.price = price;
		this.riskItem = riskItem;
		this.priceList = priceList;
		this.insurance = insurance;
	}

	public Integer getId() {
		return id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public RiskItem getRiskItem() {
		return riskItem;
	}

	public void setRiskItem(RiskItem riskItem) {
		this.riskItem = riskItem;
	}

	public PriceList getPriceList() {
		return priceList;
	}

	public void setPriceList(PriceList priceList) {
		this.priceList = priceList;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}
	
}
