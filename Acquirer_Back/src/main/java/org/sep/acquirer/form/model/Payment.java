package org.sep.acquirer.form.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.sep.acquirer.form.dto.PaymentDTO;

@Entity
public class Payment extends AbstractEntity {

	
	@Column(name="amount", nullable=false)
	private Double amount;

	@Column(name="merchantOrderId", nullable=false)
	private Long merchantOrderId;
	
	@Column(name="merchantTimestamp", nullable=false)
	private Date merchantTimestamp;
	
	@Column(name="merchantErrorURL")
	private String merchantErrorURL;

	@ManyToOne
    @JoinColumn(name = "merchant")
	private Merchant merchant;
	
	@OneToOne
	private AcquirerOrder acquirerOrder;
	
	public Payment() {
	}
	
	public Payment(Double amount, Long merchantOrderId, Date merchantTimestamp, String merchantErrorURL, Merchant merchant) {
		super();
		this.amount = amount;
		this.merchantOrderId = merchantOrderId;
		this.merchantTimestamp = merchantTimestamp;
		this.merchantErrorURL = merchantErrorURL;
		this.merchant = merchant;
	}
	
	public Payment(PaymentDTO payment, Merchant merchant) {
		super();
		this.amount = payment.getAmount();
		this.merchantOrderId = payment.getMerchantOrderId();
		this.merchantTimestamp = payment.getMerchantTimeStamp();
		this.merchantErrorURL = payment.getErrorUrl();
		this.merchant = merchant;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public Date getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Date merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public String getMerchantErrorURL() {
		return merchantErrorURL;
	}

	public void setMerchantErrorURL(String merchantErrorURL) {
		this.merchantErrorURL = merchantErrorURL;
	}
	
	
	
}
