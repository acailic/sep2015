package org.sep.acquirer.form.dto;

import java.io.Serializable;
import java.util.Date;

import org.sep.acquirer.form.model.AcquirerOrder;

public class TransactionDTO implements Serializable{

	private Long acquirerOrderId;
	private Date acquirerTimestamp;
	private String pan;
	private String cardSecCode;
	private String cardHolderName;
	private Date cardExpDate;
	private Double amount;
	
	public TransactionDTO() {
	}
	
	public TransactionDTO(AcquirerOrder order) {
		
		this.acquirerOrderId = order.getId();
		this.acquirerTimestamp = order.getAcquirerTimestamp();
		this.pan = order.getPan();
		this.cardSecCode = order.getCardSecCode();
		this.cardHolderName = order.getCardHolderName();
		this.cardExpDate = order.getCardExpDate();
		this.amount = order.getAmount();
	}

	
	public Long getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(Long acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public Date getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(Date acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
}
