package org.sep.merchant.form.dto;

import java.io.Serializable;

public class MerchantResponseDTO implements Serializable{

	private static final long serialVersionUID = 4140689012499664320L;
	
	private String paymentUrl;
	private Long paymentId; 
	
	public MerchantResponseDTO(){
		
	}

	public MerchantResponseDTO(String paymentUrl, Long paymentId) {
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
