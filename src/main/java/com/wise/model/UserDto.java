package com.wise.model;

public class UserDto {
	
	private String username;
	private String providerName;
	private String rfc;
	private String I_LIFNR;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getI_LIFNR() {
		return I_LIFNR;
	}
	public void setI_LIFNR(String i_LIFNR) {
		I_LIFNR = i_LIFNR;
	}

}
