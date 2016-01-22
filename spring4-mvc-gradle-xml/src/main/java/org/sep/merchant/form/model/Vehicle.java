package org.sep.merchant.form.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable{

	private static final long serialVersionUID = -7414343474311471122L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_vehicle")
	private Integer id;
	
	@Column (name = "type", nullable = false)
	private String vehicleType;
	
	@Column (name = "man_year", nullable = false)
	private String manufactureYear;
	
	@Column (name = "reg_num", nullable = false)
	private String registrationNumber;
	
	@Column (name = "chassis_num", nullable = false)
	private String chassisNumber;
	
	@ManyToOne
	@JoinColumn(name="id_owner")
	private Owner owner;
	
	/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name="risk_type", 
        joinColumns={@JoinColumn(name="id_vehicle")},
        inverseJoinColumns={@JoinColumn(name="id_risk_type")})
	private Set<RiskType> riskTypes = new HashSet<RiskType>();*/
	
	@OneToMany(mappedBy = "vehicle")  
	private Set<RiskTypeVehicle> riskTypeVehicles = new HashSet<RiskTypeVehicle>();

	public Vehicle(Integer id, String vehicleType, String manufactureYear,
			String registrationNumber, String chassisNumber) {
		super();
		this.id = id;
		this.vehicleType = vehicleType;
		this.manufactureYear = manufactureYear;
		this.registrationNumber = registrationNumber;
		this.chassisNumber = chassisNumber;
	}

	public Integer getId() {
		return id;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getManufactureYear() {
		return manufactureYear;
	}

	public void setManufactureYear(String manufactureYear) {
		this.manufactureYear = manufactureYear;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
}
