package org.sep.merchant.form.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "home")
public class Home  implements Serializable{

	private static final long serialVersionUID = -1272181514777826184L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_home")
	private Integer id;
	
	@Column (name = "floor_area", nullable = false)
	private Double floorArea;
	
	@Column (name = "flat_age", nullable = false)
	private Integer flatAge;
	
	@Column (name = "est_value", nullable = false)
	private BigDecimal estimatedValue;
	
	@ManyToOne
	@JoinColumn(name="id_owner")
	private Owner owner;
	
	/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name="risk_type", 
        joinColumns={@JoinColumn(name="id_home")},
        inverseJoinColumns={@JoinColumn(name="id_risk_type")})
	private Set<RiskType> riskTypes = new HashSet<RiskType>();*/
	@OneToMany(mappedBy = "home")  
	private Set<RiskTypeHome> riskTypeHomes = new HashSet<RiskTypeHome>();
	
	public Home(){
		super();
	}
	
	public Home(Double floorArea, Integer flatAge,
			BigDecimal estimatedValue) {
		super();
		this.floorArea = floorArea;
		this.flatAge = flatAge;
		this.estimatedValue = estimatedValue;
	}

	public Integer getId() {
		return id;
	}

	public Double getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Double floorArea) {
		this.floorArea = floorArea;
	}

	public Integer getFlatAge() {
		return flatAge;
	}

	public void setFlatAge(Integer flatAge) {
		this.flatAge = flatAge;
	}

	public BigDecimal getEstimatedValue() {
		return estimatedValue;
	}

	public void setEstimatedValue(BigDecimal estimatedValue) {
		this.estimatedValue = estimatedValue;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	
}
