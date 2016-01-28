package org.sep.issuer.form.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.sep.issuer.form.dto.UserDTO;

@Entity
public class User extends AbstractEntity {

	@Column(name="jmbg", nullable=false)
	private String jmbg;
	
	@Column(name="firstName", nullable=false)
	private String firstName;
  
	@Column(name="lastName", nullable=false)
	private String lastName;
	
	private String address;
	private String telNumber;
	private String email;
	
//	@OneToMany(mappedBy = "user")
//	private ArrayList<Account> accounts = new ArrayList<Account>();
	
//	@OneToMany(mappedBy = "user")
//	private ArrayList<Card> cards = new ArrayList<Card>();
	
	
	public User(String jmbg, String firstName, String lastName, String address, String telNumber, String email) {
		super();
		this.jmbg = jmbg;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.telNumber = telNumber;
		this.email = email;
	}
	
	public User(UserDTO user) {
		super();
		this.jmbg = user.getJmbg();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.address = user.getAddress();
		this.telNumber = user.getTelNumber();
		this.email = user.getEmail();
	}
	
	public User() {
		super();
	}
	


//	public ArrayList<Card> getCards() {
//		return cards;
//	}
//
//	public void setCards(ArrayList<Card> cards) {
//		this.cards = cards;
//	}

//	public ArrayList<Account> getAccounts() {
//		return accounts;
//	}
//
//	public void setAccounts(ArrayList<Account> accounts) {
//		this.accounts = accounts;
//	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
