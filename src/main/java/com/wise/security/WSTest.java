package com.wise.security;

import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;




import functions.rfc.sap.document.sap_com.Char16;
import functions.rfc.sap.document.sap_com.ZWS_LOGIN_USUARIOStub;
import functions.rfc.sap.document.sap_com.Z_FE_FM_LOGIN_USUARIO;
import functions.rfc.sap.document.sap_com.Z_FE_FM_LOGIN_USUARIOResponse;

public class WSTest {
	
	public static void main(String[] args) throws Exception {
		ZWS_LOGIN_USUARIOStub stub = new ZWS_LOGIN_USUARIOStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername("E_AMONDONEDO");
		auth.setPassword("amondonedom13");
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_LOGIN_USUARIO user = new Z_FE_FM_LOGIN_USUARIO();
		Char16 username = new Char16();
		Char16 passwd = new Char16();
		username.setChar16("10002");
		passwd.setChar16("10001");
		user.setI_USER(username);
		user.setI_PWD(passwd);
		Z_FE_FM_LOGIN_USUARIOResponse response = stub.z_FE_FM_LOGIN_USUARIO(user);
		
		System.out.println("ResponseMSG: " + response.getE_MSG());
		System.out.println("ResponseLIFNR: " + response.getE_LIFNR());
		System.out.println("ResponseID: " + response.getE_ID());
		System.out.println("ResponseType: " + response.getE_TYPE());
	}

}
