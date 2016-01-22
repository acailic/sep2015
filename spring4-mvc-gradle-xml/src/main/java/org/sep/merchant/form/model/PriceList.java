package org.sep.merchant.form.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	private Date validFrom;
	
	@Column (name = "valid_to", nullable = false)
	private Date validTo;
	
	@OneToMany(mappedBy = "priceList")  
	private Set<PriceListItem> priceListItems = new HashSet<PriceListItem>();

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

	public Set<PriceListItem> getPriceListItems() {
		return priceListItems;
	}

	public void setPriceListItems(Set<PriceListItem> priceListItems) {
		this.priceListItems = priceListItems;
	}

}
