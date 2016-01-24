package org.sep.acquirer.form.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.sep.acquirer.form.dto.MerchantDTO;


@Entity
public class Merchant extends AbstractEntity{
	
	@Column(name="name", nullable=false)
	private String name;

	@Column(name="pib", nullable=false, unique = true)
	private String pib;
	
	private String address;
	
	private String telNumber;
	
	private String email;

	@Column(name="password", nullable=false)
	private String password;
	
	@ManyToOne
    @JoinColumn(name = "acquirer")
	private Acquirer acquirer;
	
	public Merchant() {
		super();
	}

	public Merchant(MerchantDTO merc) {

		this.name = merc.getName();
		this.pib = merc.getPib();
		this.address = merc.getAddress();
		this.telNumber = merc.getTelNumber();
		this.email = merc.getEmail();
		this.password = merc.getPassword();		
	}

	public Merchant(Integer id, String name, String pib, String address, String telNumber, String email, String password, Acquirer acquirer) {
		super();
		this.id = id;
		this.name = name;
		this.pib = pib;
		this.address = address;
		this.telNumber = telNumber;
		this.email = email;
		this.password = password;
		this.acquirer = acquirer;
	}

	public Acquirer getAcquirer() {
		return acquirer;
	}

	public void setAcquirer(Acquirer acquirer) {
		this.acquirer = acquirer;
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
	
	
	
}
