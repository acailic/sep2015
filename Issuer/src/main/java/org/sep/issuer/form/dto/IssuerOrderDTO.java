package org.sep.issuer.form.dto;

import java.util.Date;

public class IssuerOrderDTO {

	private Long acquirerOrderId;  
	private Date acquirerOrderTimestamp;	
	private Double amount;
	private String pan;
	private String cardSecCode;
	private String cardHolderName;
	private Date cardExpDate;
	
	
	public IssuerOrderDTO() {
		
	}


	public IssuerOrderDTO(Long acquirerOrderId, Date acquirerOrderTimestamp, Double amount, String pan,
			String cardSecCode, String cardHolderName, Date cardExpDate) {
		super();
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerOrderTimestamp = acquirerOrderTimestamp;
		this.amount = amount;
		this.pan = pan;
		this.cardSecCode = cardSecCode;
		this.cardHolderName = cardHolderName;
		this.cardExpDate = cardExpDate;
	}


	public Long getAcquirerOrderId() {
		return acquirerOrderId;
	}


	public void setAcquirerOrderId(Long acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}


	public Date getAcquirerOrderTimestamp() {
		return acquirerOrderTimestamp;
	}


	public void setAcquirerOrderTimestamp(Date acquirerOrderTimestamp) {
		this.acquirerOrderTimestamp = acquirerOrderTimestamp;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getPan() {
		return pan;
	}


	public void setPan(String pan) {
		this.pan = pan;
	}


	public String getCardSecCode() {
		return cardSecCode;
	}


	public void setCardSecCode(String cardSecCode) {
		this.cardSecCode = cardSecCode;
	}


	public String getCardHolderName() {
		return cardHolderName;
	}


	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}


	public Date getCardExpDate() {
		return cardExpDate;
	}


	public void setCardExpDate(Date cardExpDate) {
		this.cardExpDate = cardExpDate;
	}
	
	
	
	
	
}
