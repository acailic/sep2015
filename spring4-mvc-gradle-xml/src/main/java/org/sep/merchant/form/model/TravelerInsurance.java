package org.sep.merchant.form.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "traveler_insurance")
public class TravelerInsurance implements Serializable{

	private static final long serialVersionUID = -1875050442235483545L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_traveler_insurance")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_traveler")
	private Traveler traveler;
	
	@ManyToOne
	@JoinColumn(name="id_insurance")
	private Insurance insurance;

	public TravelerInsurance(Traveler traveler, Insurance insurance) {
		super();
		this.traveler = traveler;
		this.insurance = insurance;
	}

	public Traveler getTraveler() {
		return traveler;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

}
