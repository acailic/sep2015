package org.sep.merchant.form.dto;

import java.io.Serializable;

public class ReturnUrlDTO implements Serializable{
	
	private static final long serialVersionUID = 7209717964087623438L;
	
	private String url;
	
	public ReturnUrlDTO(){
		
	}

	public ReturnUrlDTO(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
