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
@Table(name = "merchant")
public class Merchant implements Serializable{

	private static final long serialVersionUID = -3006891075936241304L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_merchant")
	private Integer id;
	
	@Column (name = "password_merchant", nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "merchant")  
	private Set<Order> orders = new HashSet<Order>();
	
	public Merchant(){
		super();
	}

	public Merchant(Integer id, String password, Set<Order> orders) {
		super();
		this.id = id;
		this.password = password;
		this.orders = orders;
	}

	public Integer getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
}
