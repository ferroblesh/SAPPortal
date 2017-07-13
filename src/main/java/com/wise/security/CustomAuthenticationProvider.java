package com.wise.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import com.wise.Constants;

import functions.rfc.sap.document.sap_com.Char1;
import functions.rfc.sap.document.sap_com.Char10;
import functions.rfc.sap.document.sap_com.Char16;
import functions.rfc.sap.document.sap_com.ZWS_LOGIN_USUARIOStub;
import functions.rfc.sap.document.sap_com.Z_FE_FM_LOGIN_USUARIO;
import functions.rfc.sap.document.sap_com.Z_FE_FM_LOGIN_USUARIOResponse;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger LOGGER = Logger.getLogger(CustomAuthenticationProvider.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Authentication authenticate(Authentication authentication){
		
		CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
		
		Z_FE_FM_LOGIN_USUARIOResponse response = details.getProvider() != null && details.getProvider().length() > 0 ? loadEmpFromWS(authentication, details) :loadFromWS(authentication);
		
		if(response == null) {
			throw new BadCredentialsException("Usuario inválido");
		} else {
			if(response.getE_TYPE().toString().equals("S")) {
				
				List<GrantedAuthority> grantedAuths = new ArrayList();
				grantedAuths.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
				Authentication auth = new UsernamePasswordAuthenticationToken(response.getE_LIFNR().toString(), authentication.getCredentials(), grantedAuths);				
				return auth;				
			}else {
				throw new AuthenticationCredentialsNotFoundException(response.getE_TYPE().toString().equals("E") ? response.getE_MSG().toString() : "");
			}
		}						
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	public Z_FE_FM_LOGIN_USUARIOResponse loadFromWS(Authentication authentication) {
		
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
	
	public Z_FE_FM_LOGIN_USUARIOResponse loadEmpFromWS(Authentication authentication, CustomWebAuthenticationDetails details) {
		
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
			Char10 i_lifnr = new Char10();
			username.setChar16(authentication.getName());
			passwd.setChar16((String)authentication.getCredentials());
			i_flag.setChar1("2");
			i_lifnr.setChar10(details.getProvider());
			user.setI_USER(username);
			user.setI_PWD(passwd);
			user.setI_FLAG(i_flag);
			user.setI_LIFNR(i_lifnr);
			Z_FE_FM_LOGIN_USUARIOResponse response = stub.z_FE_FM_LOGIN_USUARIO(user);			
			return response;			
		} catch (Exception e) {
			LOGGER.error("ERROR AL AUTENTICAR ",e);
			return null;
		}
		
	}
	
}
