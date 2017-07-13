package com.wise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceRegistry {
	
	private static ProviderService providerService;
	
	@Autowired
	public void setProviderService(ProviderService providerService) {
		ServiceRegistry.providerService = providerService;
	}
	
	public static ProviderService getProviderService() {
		return providerService;
	}
	

}
