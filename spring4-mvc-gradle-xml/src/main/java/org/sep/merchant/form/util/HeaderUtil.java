package org.sep.merchant.form.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;

public final class HeaderUtil {
	
	public static HttpHeaders getHeader(){
		 List<String> listOfAllowed = new ArrayList<String>();
		 //listOfAllowed.add("http://localhost:8082");
		 listOfAllowed.add("http://localhost:8082");
		 HttpHeaders responseHeaders = new HttpHeaders();
	     responseHeaders.put("Access-Control-Allow-Origin", listOfAllowed);
		 responseHeaders.set("Access-Control-Allow-Credentials", "true");
		 responseHeaders.set("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		 responseHeaders.set("Access-Control-Max-Age", "3600");
		 responseHeaders.set("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
		 return responseHeaders;
		
	}

}
