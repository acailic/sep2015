package org.sep.issuer.form.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.sep.issuer.form.dto.IssuerDTO;

@Entity
public class Issuer extends AbstractEntity {
	
	@Column(name="name", nullable=false)
	private String name;
  
	@Column(name="url", nullable=false)
	private String url;

	@Column(name="iin", nullable=false, length = 6, unique = true)
	private String iin;
	
	private String address;
	private String telNumber;
	private String email;
	
	
	public Issuer(String name, String url, String iin, String address, String telNumber, String email) {
		super();
		this.name = name;
		this.url = url;
		this.iin = iin;
		this.address = address;
		this.telNumber = telNumber;
		this.email = email;
	}
	
	public Issuer(IssuerDTO acq) {
		super();
		this.name = acq.getName();
		this.url = acq.getUrl();
		this.iin = acq.getIin();
		this.address = acq.getAddress();
		this.telNumber = acq.getTelNumber();
		this.email = acq.getEmail();
	}
	
	public Issuer() {
		super();
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIin() {
		return iin;
	}
	public void setIin(String iin) {
		this.iin = iin;
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
	
	
}
