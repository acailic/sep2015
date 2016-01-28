package org.sep.acquirer.form.dto;

import java.util.Date;

import org.sep.acquirer.form.model.AcquirerOrder.OrderStateEnum;

public class PccResponseDTO {

	private Long acquirerOrderId;  
	private Date acquirerOrderTimestamp;
	private OrderStateEnum state;
	private String errorMsg;
	
	
	public PccResponseDTO() {
		super();
	}
			
	public String getErrorMsg() {
		return errorMsg;
	}


	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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
	public OrderStateEnum getState() {
		return state;
	}
	public void setState(OrderStateEnum state) {
		this.state = state;
	}
	
	
}

