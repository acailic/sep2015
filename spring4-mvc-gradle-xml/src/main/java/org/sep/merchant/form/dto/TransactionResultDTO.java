package org.sep.merchant.form.dto;

import java.io.Serializable;
import java.sql.Date;

public class TransactionResultDTO implements Serializable{

	private static final long serialVersionUID = 8773629548563204818L;
	
	private String transactionResult;
	private Integer merchantOrderId;
	private Integer acquirerOrderId;
	private Date acquirerTimestamp;
	private Integer paymentId;
	
	public TransactionResultDTO(){
		
	}

	public TransactionResultDTO(String transactionResult, Integer merchantOrderId,
			Integer acquirerOrderId, Date acquirerTimestamp, Integer paymentId) {
		super();
		this.transactionResult = transactionResult;
		this.merchantOrderId = merchantOrderId;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.paymentId = paymentId;
	}
	
	public String getTransactionResult() {
		return transactionResult;
	}

	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}

	public Integer getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Integer merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public Integer getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(Integer acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public Date getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(Date acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

}
