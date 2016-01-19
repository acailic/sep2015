package org.sep.merchant.form.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "price_list")
public class PriceList implements Serializable{

	private static final long serialVersionUID = 2888273670881408269L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_price_list", nullable = false)
	private Integer id;
	
	@Column (name = "valid_from", nullable = false)
	//@Size(min=2, max=50, message="{validation.user.firstName}")
	//@UnicodePattern(pattern=UnicodePattern.FIRST_NAME_PATTERN, message="{validation.FIRST_NAME_PATTERN.format.message}")
	private Date validFrom;
	
	@Column (name = "valid_to", nullable = false)
	//@Size(min=2, max=50, message="{validation.user.firstName}")
	//@UnicodePattern(pattern=UnicodePattern.FIRST_NAME_PATTERN, message="{validation.FIRST_NAME_PATTERN.format.message}")
	private Date validTo;

	public PriceList(Integer id, Date validFrom, Date validTo) {
		super();
		this.id = id;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public Integer getId() {
		return id;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}


}
