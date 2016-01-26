package org.sep.acquirer.form.dto;

import java.util.Date;

public class PaymentDTO {

	private Long merchantId;
	private String merchantPass;
	private Double amount;
	private Long merchantOrderId;
	private Date merchantTimeStamp;
	private String errorUrl;
	
	public PaymentDTO() {
	}

	public PaymentDTO(Long merchantId, String merchantPass, Double amount, Long merchantOrderId,
			Date merchantTimeStamp, String errorUrl) {
		super();
		this.merchantId = merchantId;
		this.merchantPass = merchantPass;
		this.amount = amount;
		this.merchantOrderId = merchantOrderId;
		this.merchantTimeStamp = merchantTimeStamp;
		this.errorUrl = errorUrl;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantPass() {
		return merchantPass;
	}

	public void setMerchantPass(String merchantPass) {
		this.merchantPass = merchantPass;
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

	public Date getMerchantTimeStamp() {
		return merchantTimeStamp;
	}

	public void setMerchantTimeStamp(Date merchantTimeStamp) {
		this.merchantTimeStamp = merchantTimeStamp;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
	
	
	
}
