package com.wise.dao;

import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import com.wise.Constants;

import functions.rfc.sap.document.sap_com.Char1;
import functions.rfc.sap.document.sap_com.Char10;
import functions.rfc.sap.document.sap_com.Char16;
import functions.rfc.sap.document.sap_com.ZWS_LOGIN_USUARIOStub;
import functions.rfc.sap.document.sap_com.ZWS_OBTIENE_CONFIGStub;
import functions.rfc.sap.document.sap_com.Z_FE_FM_LOGIN_USUARIO;
import functions.rfc.sap.document.sap_com.Z_FE_FM_LOGIN_USUARIOResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_OBTIENE_CONFIG;
import functions.rfc.sap.document.sap_com.Z_FE_FM_OBTIENE_CONFIGResponse;

@Repository
public class UserDao {
	
	public final static Logger LOGGER = Logger.getLogger(UserDao.class);
	
	public Z_FE_FM_LOGIN_USUARIOResponse findUserFromWS(Authentication authentication) {
		try {
			ZWS_LOGIN_USUARIOStub stub = new ZWS_LOGIN_USUARIOStub();
			Options options = stub._getServiceClient().getOptions();
			HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
			auth.setPreemptiveAuthentication(true);
			auth.setUsername(Constants.WISE_WSDL_USER);
			auth.setPassword(Constants.WISE_WSDL_PASS);
			options.setProperty(HTTPConstants.AUTHENTICATE,auth);
			Z_FE_FM_LOGIN_USUARIO user = new Z_FE_FM_LOGIN_USUARIO();
			Char16 username = new Char16();
			Char16 passwd = new Char16();
			Char1 i_flag = new Char1();
			username.setChar16(authentication.getName());
			passwd.setChar16((String)authentication.getCredentials());
			i_flag.setChar1("1");
			user.setI_USER(username);
			user.setI_PWD(passwd);
			user.setI_FLAG(i_flag);
			Z_FE_FM_LOGIN_USUARIOResponse response = stub.z_FE_FM_LOGIN_USUARIO(user);
			
			return response;
			
		} catch (Exception e) {
			LOGGER.error("ERROR AL AUTENTICAR ",e);
			return null;
		}
		
	}

	public Z_FE_FM_OBTIENE_CONFIGResponse findMenuConfig(String lifnr) {
		try {
			ZWS_OBTIENE_CONFIGStub stub = new ZWS_OBTIENE_CONFIGStub();
			Options options = stub._getServiceClient().getOptions();
			HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
			auth.setPreemptiveAuthentication(true);
			auth.setUsername(Constants.WISE_WSDL_USER);
			auth.setPassword(Constants.WISE_WSDL_PASS);
			options.setProperty(HTTPConstants.AUTHENTICATE,auth);
			Z_FE_FM_OBTIENE_CONFIG config = new Z_FE_FM_OBTIENE_CONFIG();
			Char10 i_lifnr = new Char10();
			i_lifnr.setChar10(lifnr);
			
			config.setI_LIFNR(i_lifnr);
			Z_FE_FM_OBTIENE_CONFIGResponse response = stub.z_FE_FM_OBTIENE_CONFIG(config);
			
			return response;
		} catch (Exception e) {
			LOGGER.error("ERROR getting OBTIENE CONFIG",e);
			return null;
		}
	}
}
