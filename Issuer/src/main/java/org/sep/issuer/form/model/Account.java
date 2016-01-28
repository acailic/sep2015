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
	
//	@OneToMany(mappedBy = "account")
//	private ArrayList<Card> cards = new ArrayList<Card>();
	
	public Account(Double balance, Long number) {
		super();
		this.balance = balance;
		this.number = number;
	}
		
	public Account() {
		super();
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public ArrayList<Card> getCards() {
//		return cards;
//	}
//
//	public void setCards(ArrayList<Card> cards) {
//		this.cards = cards;
//	}

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
