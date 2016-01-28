package org.sep.pcc.form.dto;

import java.util.Date;

import org.sep.pcc.form.model.Transaction;

public class IssuerRequestDTO {


	private Long acquirerOrderId;  
	private Date acquirerOrderTimestamp;	
	private Double amount;
	private String pan;
	private String cardSecCode;
	private String cardHolderName;
	private Date cardExpDate;
	
	public IssuerRequestDTO(Transaction trans) {
		super();
		this.acquirerOrderId = trans.getAcquirerOrderId();
		this.acquirerOrderTimestamp = trans.getAcquirerOrderTimestamp();
		this.amount = trans.getAmount();
		this.pan = trans.getPan();
		this.cardSecCode = trans.getCardSecCode();
		this.cardHolderName = trans.getCardHolderName();
		this.cardExpDate = trans.getCardExpDate();
	}
	
	
	public IssuerRequestDTO(Long acquirerOrderId, Date acquirerOrderTimestamp, Double amount, String pan,
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
	
	
	public IssuerRequestDTO() {
		super();
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
