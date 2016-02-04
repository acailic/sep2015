package org.sep.acquirer.form.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.sep.acquirer.form.dto.AcquirerOrderDTO;

@Entity
public class AcquirerOrder extends AbstractEntity{

	public enum OrderStateEnum {
		PENDING, SUCCESSFULL, UNSUCCESSFULL
	}
	
	
	@Column(name="acquirerTimestamp", nullable=false)
	private Date acquirerTimestamp;
	
	@Column(name="pan", nullable = false, length = 16)
	private String pan;
	
	@Column(name="cardSecCode", nullable = false, length = 3)
	private String cardSecCode;
	
	@Column(name="cardHolderName", nullable = false)
	private String cardHolderName;
	
	@Column(name="cardExpDate", nullable = false)
	private Date cardExpDate;
	
	@Column(name="amount", nullable = false)
	private Double amount;
	
	@Column(name="orderState", nullable = false)
	private OrderStateEnum orderState;
	
	@OneToOne
	private Payment payment;

	public AcquirerOrder() {
	}

	public AcquirerOrder(Date acquirerTimestamp, String pan, String cardSecCode, String cardHolderName,
			Date cardExpDate, Double amount, OrderStateEnum orderState, Payment payment) {
		super();
		this.acquirerTimestamp = acquirerTimestamp;
		this.pan = pan;
		this.cardSecCode = cardSecCode;
		this.cardHolderName = cardHolderName;
		this.cardExpDate = cardExpDate;
		this.amount = amount;
		this.orderState = orderState;
		this.payment = payment;
	}
	
	public AcquirerOrder(AcquirerOrderDTO order, Payment payment, OrderStateEnum state) {
		super();
		this.acquirerTimestamp = order.getAcquirerTimeStamp();
		this.pan = order.getPan();
		this.cardSecCode = order.getCardSecCode();
		this.cardHolderName = order.getCardHolderName();
		this.cardExpDate = order.getCardExpDate();
		this.amount = order.getAmount();
		this.orderState = state;
		this.payment = payment;
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

	public OrderStateEnum getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderStateEnum orderState) {
		this.orderState = orderState;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
}
