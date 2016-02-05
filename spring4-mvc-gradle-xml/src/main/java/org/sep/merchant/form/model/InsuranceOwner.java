package org.sep.merchant.form.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "insurance_owner")
public class InsuranceOwner implements Serializable{

	private static final long serialVersionUID = 3411348686353887416L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_ins_owner")
	private Integer id;
	
	@Column (name = "first_name_ins_owner", nullable = false)
	private String firstName;
	
	@Column (name = "last_name_ins_owner", nullable = false)
	private String lastName;
	
	@Column (name = "jmbg_ins_owner", nullable = true)
	private String jmbg;
	
	@Column (name = "passport_num_ins_owner", nullable = true)
	private String passNum;
	
	@Column (name = "address_ins_owner", nullable = true)
	private String address;
	
	@Column (name = "email_ins_owner", nullable = false)
	private String email;
	
	@Column (name = "telephone_ins_owner", nullable = true)
	private String telephoneNumber;
	
	@OneToMany(mappedBy = "insuranceOwner")  
	private Set<Insurance> insurances = new HashSet<Insurance>();
	
	public InsuranceOwner(){
		super();
	}

	public InsuranceOwner(String firstName, String lastName,
			String jmbg, String address,
			String telephoneNumber, String email, String passNum) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.jmbg = jmbg;
		this.address = address;
		this.telephoneNumber = telephoneNumber;
		this.email = email;
		this.passNum = passNum;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Insurance> getInsurances() {
		return insurances;
	}

	public void setInsurances(Set<Insurance> insurances) {
		this.insurances = insurances;
	}
	
}
