package org.sep.acquirer.form.dto;

public class TokenResponseDTO {

	private Long paymentId;
	private Double amount;
	private String CSRFToken;
	

	public TokenResponseDTO() {
	}

	
	public TokenResponseDTO(Long paymentId, Double amount, String cSRFToken) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		CSRFToken = cSRFToken;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCSRFToken() {
		return CSRFToken;
	}
	public void setCSRFToken(String cSRFToken) {
		CSRFToken = cSRFToken;
	}
	
	
}
