package org.sep.issuer.form.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Account extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(name="issuer", nullable=false)
	private Issuer issuer;
	
	@Column(name="balance", nullable=false)
	private Double balance;
  
	@Column(name="number", nullable=false)
	private Long number;
	
	@ManyToOne
	@JoinColumn(name="user", nullable = false)
	private User user;
	
	public Account(Double balance, Long number) {
		super();
		this.balance = balance;
		this.number = number;
	}
		
	public Account() {
		super();
	}
	
	public  String toString(){
		
		
		return issuer.getName() + " " + user.getFirstName() + " " + number + " " + balance + " " + id;
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Issuer getIssuer() {
		return issuer;
	}

	public void setIssuer(Issuer issuer) {
		this.issuer = issuer;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
	
	
}
