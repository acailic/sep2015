package org.sep.issuer.form.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.sep.issuer.form.dto.IssuerOrderDTO;

@Entity
public class IssuerOrder extends AbstractEntity {

	public enum OrderStateEnum {
		PENDING, SUCCESSFULL, UNSUCCESSFULL
	}
		
	@Column(name="acquirerOrderId", nullable=false)
	private Long acquirerOrderId;
  
	@Column(name="acquirerOrderTimestamp", nullable=false)
	private Date acquirerOrderTimestamp;

	@Column(name="issuerOrderTimestamp", nullable=false)
	private Date issuerOrderTimestamp;
	
	@ManyToOne
	@JoinColumn(name="card", nullable = false)
	private Card card;
	
	private Double amount;
	private OrderStateEnum state;
	
	
	

	public IssuerOrder(Long acquirerOrderId, Date acquirerOrderTimestamp, Date issuerOrderTimestamp, Card card,
			Double amount, OrderStateEnum state) {
		super();
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerOrderTimestamp = acquirerOrderTimestamp;
		this.issuerOrderTimestamp = issuerOrderTimestamp;
		this.card = card;
		this.amount = amount;
		this.state = state;
	}

	public IssuerOrder(IssuerOrderDTO ord) {
		super();
		this.acquirerOrderId = ord.getAcquirerOrderId();
		this.acquirerOrderTimestamp = ord.getAcquirerOrderTimestamp();
		this.amount = ord.getAmount();
		this.state = OrderStateEnum.PENDING;
	}
	
	public IssuerOrder() {
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

	public Date getIssuerOrderTimestamp() {
		return issuerOrderTimestamp;
	}

	public void setIssuerOrderTimestamp(Date issuerOrderTimestamp) {
		this.issuerOrderTimestamp = issuerOrderTimestamp;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public OrderStateEnum getState() {
		return state;
	}

	public void setState(OrderStateEnum state) {
		this.state = state;
	}
	
	
}
