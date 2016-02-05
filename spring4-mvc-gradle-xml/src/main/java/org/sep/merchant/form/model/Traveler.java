package org.sep.merchant.form.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "traveler")
public class Traveler implements Serializable{
	
	private static final long serialVersionUID = -8696328818648997721L;

	@Id
	@GeneratedValue
	@Column(name = "id_traveler")
	private Integer id;
	
	@Column (name = "first_name_traveler", nullable = false)
	private String firstName;
	
	@Column (name = "last_name_traveler", nullable = false)
	private String lastName;
	
	@Column (name = "passport_num_traveler", nullable = false)
	private String passportNumber;
	
	@Column (name = "jmbg_traveler", nullable = false)
	private String jmbg;
	
	@Column (name = "address_traveler", nullable = true)
	private String address;
	
	@Column (name = "telephone_num_traveler", nullable = false)
	private String telephoneNumber;
	
	/*@ManyToMany(fetch = FetchType.LAZY, mappedBy = "travelers")
	private Set<Insurance> insurances = new HashSet<Insurance>();*/
	
	@OneToMany(mappedBy = "traveler", cascade = {CascadeType.MERGE})  
	private Set<TravelerInsurance> travelerInsurances = new HashSet<TravelerInsurance>();
	
	public Traveler(){
		super();
	}

	public Traveler(String firstName, String lastName,
			String passportNumber, String jmbg, String address,
			String telephoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.passportNumber = passportNumber;
		this.jmbg = jmbg;
		this.address = address;
		this.telephoneNumber = telephoneNumber;
	}

	public Integer getId() {
		return id;
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

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	@Override
    public String toString() {
        return "Traveler [jmbg=" + jmbg + ", passport_num=" + passportNumber + ", tel_num=" + telephoneNumber
                + ", first_name=" + firstName + ", last_name=" + lastName + "]";
    }

}
