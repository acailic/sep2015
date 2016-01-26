package org.sep.acquirer.form.dto;

public class PaymentResponseDTO {

	private String paymentUrl;
	private Long paymentId;
	
	
	public PaymentResponseDTO() {
	}


	public PaymentResponseDTO(String paymentUrl, Long paymentId) {
		super();
		this.paymentUrl = paymentUrl;
		this.paymentId = paymentId;
	}


	public String getPaymentUrl() {
		return paymentUrl;
	}


	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}


	public Long getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	
	
}
