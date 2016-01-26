package org.sep.pcc.form.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.sep.pcc.form.dto.TransactionDTO;

@Entity
public class Transaction extends AbstractEntity {

	public enum OrderStateEnum {
		PENDING, SUCCESSFULL, UNSUCCESSFULL
	}
	
	@Column(name="acquirerOrderId", nullable=false)
	private Long acquirerOrderId;
	
	@Column(name="acquirerTimestamp", nullable=false)
	private Date acquirerTimestamp;
	
	@Column(name="pan", nullable=false)
	private String pan;
	
	@Column(name="cardSecCode", nullable=false)
	private String cardSecCode;

	@Column(name="cardHolderName", nullable=false)
	private String cardHolderName;

	@Column(name="cardExpDate", nullable=false)
	private Date cardExpDate;
	
	@Column(name="amount", nullable=false)
	private Double amount;

	@Column(name="issuerOrderId")
	private Long issuerOrderId;
	
	@Column(name="issuerTimestamp")
	private Date issuerTimestamp;

	@Column(name="orderState", nullable=false)
	private OrderStateEnum orderState;
	
	public Transaction() {
	}
	
	
	public Transaction(Long acquirerOrderId, Date acquirerTimestamp, String pan, String cardSecCode,
			String cardHolderName, Date cardExpDate, Double amount, Long issuerOrderId, Date issuerTimestamp,
			OrderStateEnum orderState) {
		super();
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.pan = pan;
		this.cardSecCode = cardSecCode;
		this.cardHolderName = cardHolderName;
		this.cardExpDate = cardExpDate;
		this.amount = amount;
		this.issuerOrderId = issuerOrderId;
		this.issuerTimestamp = issuerTimestamp;
		this.orderState = orderState;
	}


	public Transaction(TransactionDTO trans) {
		this.acquirerOrderId = trans.getAcquirerOrderId();
		this.acquirerTimestamp = trans.getAcquirerTimestamp();
		this.pan = trans.getPan();
		this.cardSecCode = trans.getCardSecCode();
		this.cardHolderName = trans.getCardHolderName();
		this.cardExpDate = trans.getCardExpDate();
		this.amount = trans.getAmount();
		this.orderState = OrderStateEnum.PENDING;
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

	public Long getIssuerOrderId() {
		return issuerOrderId;
	}

	public void setIssuerOrderId(Long issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}

	public Date getIssuerTimestamp() {
		return issuerTimestamp;
	}

	public void setIssuerTimestamp(Date issuerTimestamp) {
		this.issuerTimestamp = issuerTimestamp;
	}

	public OrderStateEnum getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderStateEnum orderState) {
		this.orderState = orderState;
	}
	
	
	
}
