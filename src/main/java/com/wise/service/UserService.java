package com.wise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.wise.dao.UserDao;

import functions.rfc.sap.document.sap_com.Z_FE_FM_LOGIN_USUARIOResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_OBTIENE_CONFIGResponse;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public Z_FE_FM_LOGIN_USUARIOResponse getUserFromWS(Authentication auth){
		return userDao.findUserFromWS(auth);
	}
	
	public Z_FE_FM_OBTIENE_CONFIGResponse getMenuConfig(String lifnr) {
		return userDao.findMenuConfig(lifnr);
	}
}
