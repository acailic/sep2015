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
@Table(name = "owner")
public class Owner implements Serializable{

	private static final long serialVersionUID = 5933146910465203391L;

	@Id
	@GeneratedValue
	@Column(name = "id_owner")
	private Integer id;
	
	@Column (name = "first_name_owner", nullable = false)
	private String firstName;
	
	@Column (name = "last_name_owner", nullable = false)
	private String lastName;
	
	@Column (name = "jmbg_owner", nullable = false)
	private String jmbg;
	
	@OneToMany(mappedBy = "owner")  
	private Set<Home> homes = new HashSet<Home>();
	
	@OneToMany(mappedBy = "owner")  
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();

	public Owner(Integer id, String firstName, String lastName, String jmbg) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jmbg = jmbg;
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

	public Set<Home> getHomes() {
		return homes;
	}

	public void setHomes(Set<Home> homes) {
		this.homes = homes;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
}
