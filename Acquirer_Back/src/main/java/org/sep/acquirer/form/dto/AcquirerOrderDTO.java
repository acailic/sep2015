package org.sep.acquirer.form.dto;

import java.util.Date;

public class AcquirerOrderDTO {

	private Long paymentId;
	private Date acquirerTimeStamp;
	private String pan;
	private String cardSecCode;
	private String cardHolderName;
	private String CSRFToken;
	private Date cardExpDate;
	private Double amount;
	
	public AcquirerOrderDTO() {
	}
		
	public AcquirerOrderDTO(Long paymentId, Date acquirerTimeStamp, String pan, String cardSecCode, String cardHolderName, Date cardExpDate, Double amount) {
		super();
		this.paymentId = paymentId;
		this.acquirerTimeStamp = acquirerTimeStamp;
		this.pan = pan;
		this.cardSecCode = cardSecCode;
		this.cardHolderName = cardHolderName;
		this.cardExpDate = cardExpDate;
		this.amount = amount;
	}

	
	public String getCSRFToken() {
		return CSRFToken;
	}

	public void setCSRFToken(String cSRFToken) {
		CSRFToken = cSRFToken;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Date getAcquirerTimeStamp() {
		return acquirerTimeStamp;
	}
	public void setAcquirerTimeStamp(Date acquirerTimeStamp) {
		this.acquirerTimeStamp = acquirerTimeStamp;
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
	public Date getCardExpDate() {
		return cardExpDate;
	}
	public void setCardExpDate(Date cardExpDate) {
		this.cardExpDate = cardExpDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
}
