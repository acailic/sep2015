package org.sep.acquirer.form.dto;


public class MerchantDTO {

	
	private String name;
	private String pib;
	private String address;
	private String telNumber;
	private String email;
	private String password;
	private Long acquirerId;
	
	public MerchantDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getAcquirerId() {
		return acquirerId;
	}

	public void setAcquirerId(Long acquirerId) {
		this.acquirerId = acquirerId;
	}
	
	
}
