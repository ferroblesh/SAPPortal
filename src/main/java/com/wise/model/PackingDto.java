package com.wise.model;

import java.util.List;

public class PackingDto {
	
	private String ebeln;
	private String indicator;
	private List<PackingListDto> packingList;
	public String getEbeln() {
		return ebeln;
	}
	public void setEbeln(String ebeln) {
		this.ebeln = ebeln;
	}
	public String getIndicator() {
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	public List<PackingListDto> getPackingList() {
		return packingList;
	}
	public void setPackingList(List<PackingListDto> packingList) {
		this.packingList = packingList;
	}

}
