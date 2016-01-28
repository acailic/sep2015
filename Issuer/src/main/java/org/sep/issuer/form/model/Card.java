package org.sep.issuer.form.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Card extends AbstractEntity {
	
	@Column(name="pan", nullable=false, length = 16, unique = true)
	private String pan;
  
	@Column(name="cardHolderName", nullable=false)
	private String cardHolderName;

	@Column(name="cardExpDate", nullable=false)
	private Date cardExpDate;
	
	@ManyToOne
	@JoinColumn(name="user", nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="account", nullable=false)
	private Account account;
	
	
	public Card(String pan, String cardHolderName, Date cardExpDate) {
		super();
		this.pan = pan;
		this.cardHolderName = cardHolderName;
		this.cardExpDate = cardExpDate;
	}
		
	public Card() {
		super();
	}

	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Date getCardExpDate() {
		return cardExpDate;
	}

	public void setCardExpDate(Date cardExpDate) {
		this.cardExpDate = cardExpDate;
	}
	
	
	
	
}
