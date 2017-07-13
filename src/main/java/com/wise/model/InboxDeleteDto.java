package com.wise.model;

public class InboxDeleteDto {
	
	String e_type;
	String e_msg;
	String invoice;
	public String getE_type() {
		return e_type;
	}
	public void setE_type(String e_type) {
		this.e_type = e_type;
	}
	public String getE_msg() {
		return e_msg;
	}
	public void setE_msg(String e_msg) {
		this.e_msg = e_msg;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

}
