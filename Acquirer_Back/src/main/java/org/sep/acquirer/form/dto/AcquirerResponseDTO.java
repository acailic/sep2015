package org.sep.acquirer.form.dto;

import java.util.Date;

import org.sep.acquirer.form.model.AcquirerOrder.OrderStateEnum;

public class AcquirerResponseDTO {

	private Long merchantOrderId;
	private Date merchantTimeStamp;
	private Long acquirerOrderId;
	private Date acquirerTimeStamp;
	private Long paymentId;
	private OrderStateEnum state;
	private String errorMsg;
	private String errorUrl;
	
	
	public AcquirerResponseDTO(Long merchantOrderId, Date merchantTimeStamp, Long acquirerOrderId,
			Date acquirerTimeStamp, Long paymentId, String errorMsg, String errorUrl) {
		super();
		this.merchantOrderId = merchantOrderId;
		this.merchantTimeStamp = merchantTimeStamp;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimeStamp = acquirerTimeStamp;
		this.paymentId = paymentId;
		this.errorMsg = errorMsg;
		this.errorUrl = errorUrl;
	}
	public AcquirerResponseDTO() {
		super();
	}
	
	
	public OrderStateEnum getState() {
		return state;
	}
	public void setState(OrderStateEnum state) {
		this.state = state;
	}
	public Long getMerchantOrderId() {
		return merchantOrderId;
	}
	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}
	public Date getMerchantTimeStamp() {
		return merchantTimeStamp;
	}
	public void setMerchantTimeStamp(Date merchantTimeStamp) {
		this.merchantTimeStamp = merchantTimeStamp;
	}
	public Long getAcquirerOrderId() {
		return acquirerOrderId;
	}
	public void setAcquirerOrderId(Long acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}
	public Date getAcquirerTimeStamp() {
		return acquirerTimeStamp;
	}
	public void setAcquirerTimeStamp(Date acquirerTimeStamp) {
		this.acquirerTimeStamp = acquirerTimeStamp;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getErrorUrl() {
		return errorUrl;
	}
	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
	
	
}
