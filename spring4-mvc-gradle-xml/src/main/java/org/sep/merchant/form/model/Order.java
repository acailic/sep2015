package org.sep.merchant.form.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class Order implements Serializable{

	private static final long serialVersionUID = -1547623124344351880L;

	@Id
	@GeneratedValue
	@Column(name = "id_order")
	private Integer id;
	
	@Column (name = "amount", nullable = false)
	private Double amount;
	
	@Column (name = "merchant_timestamp", nullable = false)
	private Date merchantTimestamp;
	
	@Column (name = "error_url", nullable = false)
	private String errorUrl;
	
	@Column (name = "success_url", nullable = false)
	private String successUrl;
	
	@Column (name = "failed_url", nullable = false)
	private String failedUrl;
	
	@ManyToOne
	@JoinColumn(name="id_merchant")
	private Merchant merchant;
	
	@ManyToOne
	@JoinColumn(name="id_insurance")
	private Insurance insurance;
	
	public Order(){
		
	}

	public Order(Integer id, Double amount, Date merchantTimestamp,
			String errorUrl, String successUrl, String failedUrl) {
		super();
		this.id = id;
		this.amount = amount;
		this.merchantTimestamp = merchantTimestamp;
		this.errorUrl = errorUrl;
		this.successUrl = successUrl;
		this.failedUrl = failedUrl;
	}

	public Integer getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Date merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getFailedUrl() {
		return failedUrl;
	}

	public void setFailedUrl(String failedUrl) {
		this.failedUrl = failedUrl;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}
	
}
