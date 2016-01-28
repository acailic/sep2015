package org.sep.issuer.form.dto;

import java.util.Date;

import org.sep.issuer.form.model.IssuerOrder;
import org.sep.issuer.form.model.IssuerOrder.OrderStateEnum;

public class TransactionResponseDTO {

	private Long acquirerOrderId;  
	private Date acquirerOrderTimestamp;	
	private Long issuerOrderId;  
	private Date issuerOrderTimestamp;	
	private OrderStateEnum state;
		
	public TransactionResponseDTO() {
		
	}

	public TransactionResponseDTO(IssuerOrder order) {
		super();
		this.acquirerOrderId = order.getAcquirerOrderId();
		this.acquirerOrderTimestamp = order.getAcquirerOrderTimestamp();
		this.issuerOrderId = order.getId();
		this.issuerOrderTimestamp = order.getIssuerOrderTimestamp();
		this.state = order.getState();
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

	public Long getIssuerOrderId() {
		return issuerOrderId;
	}

	public void setIssuerOrderId(Long issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}

	public Date getIssuerOrderTimestamp() {
		return issuerOrderTimestamp;
	}

	public void setIssuerOrderTimestamp(Date issuerOrderTimestamp) {
		this.issuerOrderTimestamp = issuerOrderTimestamp;
	}

	public OrderStateEnum getState() {
		return state;
	}

	public void setState(OrderStateEnum state) {
		this.state = state;
	}

	
}
