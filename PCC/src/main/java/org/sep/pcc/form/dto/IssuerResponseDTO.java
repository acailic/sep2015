package org.sep.pcc.form.dto;

import java.util.Date;

import org.sep.pcc.form.model.Transaction.OrderStateEnum;

public class IssuerResponseDTO {


	private Long acquirerOrderId;  
	private Date acquirerOrderTimestamp;	
	private Long issuerOrderId;  
	private Date issuerOrderTimestamp;	
	private OrderStateEnum state;
	
	
	public IssuerResponseDTO() {
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
