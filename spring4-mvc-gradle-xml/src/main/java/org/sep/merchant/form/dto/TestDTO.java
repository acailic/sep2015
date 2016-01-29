package org.sep.merchant.form.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9194265702216930006L;
	
	private Set<TravelerDTO> lista = new HashSet<TravelerDTO>();
	
	public TestDTO(){
		
	}

	public Set<TravelerDTO> getLista() {
		return lista;
	}

	public void setLista(Set<TravelerDTO> lista) {
		this.lista = lista;
	}

	
}
