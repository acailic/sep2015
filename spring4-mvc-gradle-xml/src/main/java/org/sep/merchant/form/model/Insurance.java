package org.sep.merchant.form.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	//@Size(min=2, max=50, message="{validation.user.duration}")
	//@UnicodePattern(pattern=UnicodePattern.FIRST_NAME_PATTERN, message="{validation.FIRST_NAME_PATTERN.format.message}")
	private String duration;
	
	@Column (name = "basic_price", nullable = false)
	//@Size(min=2, max=50, message="{validation.user.username}")
	private BigDecimal basicPrice;
	
	@Column (name = "total_price", nullable = false)
	//@Size(min=2, max=50, message="{validation.user.username}")
	private BigDecimal totalPrice;
	
	@Column (name = "num_of_people", nullable = false)
	//@Size(min=2, max=50, message="{validation.user.username}")
	private Integer numOfPeople;

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

	
}
