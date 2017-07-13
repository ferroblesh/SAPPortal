package com.wise.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String provider;

	public CustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		provider = request.getParameter("j_emp");
	}
	
	public String getProvider() {
		return provider;
	}

}
