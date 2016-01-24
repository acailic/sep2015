package org.sep.merchant.form.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "insurance")
public class Insurance implements Serializable{
	
	private static final long serialVersionUID = -2667074839204711252L;

	@Id
	@GeneratedValue
	@Column(name = "id_insurance")
	private Integer id;
	
	@Column (name = "duration", nullable = false)
	private String duration;
	
	@Column (name = "basic_price", nullable = false)
	private BigDecimal basicPrice;
	
	@Column (name = "total_price", nullable = false)
	private BigDecimal totalPrice;
	
	@Column (name = "num_of_people", nullable = false)
	private Integer numOfPeople;
	
	@OneToMany(mappedBy = "insurance")  
	private Set<PriceListItem> priceListItems;
	
	@OneToMany(mappedBy = "insurance")  
	private Set<Order> orders;
	
	@ManyToOne
	@JoinColumn(name="id_ins_owner")
	private InsuranceOwner insuranceOwner;
	
	/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name="risk_item", 
        joinColumns={@JoinColumn(name="id_insurance")},
        inverseJoinColumns={@JoinColumn(name="id_risk_item")})
	private Set<RiskItem> riskItems = new HashSet<RiskItem>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name="traveler", 
        joinColumns={@JoinColumn(name="id_insurance")},
        inverseJoinColumns={@JoinColumn(name="id_traveler")})
	private Set<Traveler> travelers = new HashSet<Traveler>();*/
	
	@OneToMany(mappedBy = "insurance")  
	private Set<RiskItemInsurance> riskItemInsurances = new HashSet<RiskItemInsurance>();
	
	@OneToMany(mappedBy = "insurance")  
	private Set<TravelerInsurance> travelerInsurances = new HashSet<TravelerInsurance>();
	
	public Insurance(){
		super();
	}

	public Insurance(Integer id, String duration, BigDecimal basicPrice,
			BigDecimal totalPrice, Integer numOfPeople) {
		super();
		this.id = id;
		this.duration = duration;
		this.basicPrice = basicPrice;
		this.totalPrice = totalPrice;
		this.numOfPeople = numOfPeople;
	}

	public Integer getId() {
		return id;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public BigDecimal getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(BigDecimal basicPrice) {
		this.basicPrice = basicPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(Integer numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	public Set<PriceListItem> getPriceListItems() {
		return priceListItems;
	}

	public void setPriceListItems(Set<PriceListItem> priceListItems) {
		this.priceListItems = priceListItems;
	}

	public InsuranceOwner getInsuranceOwner() {
		return insuranceOwner;
	}

	public void setInsuranceOwner(InsuranceOwner insuranceOwner) {
		this.insuranceOwner = insuranceOwner;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
}
