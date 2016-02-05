package org.sep.merchant.form.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "risk_type_info")
public class RiskTypeInfo implements Serializable{

	private static final long serialVersionUID = 8943712844789771300L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_risk_type_info")
	private Integer id;
	
	@Column (name = "info_value", nullable = false)
	private String infoValue;
	
	@ManyToOne
	@JoinColumn(name="id_risk_type")
	private RiskType type;

	public RiskTypeInfo(Integer id, String infoValue, RiskType type) {
		super();
		this.id = id;
		this.infoValue = infoValue;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public String getInfoValue() {
		return infoValue;
	}

	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
	}

	public RiskType getType() {
		return type;
	}

	public void setType(RiskType type) {
		this.type = type;
	}
	
}
