package org.sep.pcc.form.util;

import org.springframework.http.HttpHeaders;

public final class HeaderUtil {
	
	public static HttpHeaders getHeader(){
		
		 HttpHeaders responseHeaders = new HttpHeaders();
	     responseHeaders.set("Access-Control-Allow-Origin", "http://localhost:8080");
		 responseHeaders.set("Access-Control-Allow-Credentials", "true");
		 responseHeaders.set("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		 responseHeaders.set("Access-Control-Max-Age", "3600");
		 responseHeaders.set("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
		 return responseHeaders;
		
	}

}
