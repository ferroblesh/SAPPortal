package com.wise;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Constants {
	public static String WISE_WSDL_USER = "E_AMONDONEDO";
	public static String WISE_WSDL_PASS = "amondonedom14";
	private final static Logger LOGGER = Logger.getLogger(Constants.class);
	
	private static Properties appProperties = new Properties();
	
	static {
		try {
			InputStream fileInputStream = Constants.class.getClassLoader().getResourceAsStream("app.properties");
			if(fileInputStream == null) {
				String error = "cannot find app.properties";
				LOGGER.error(error);
				throw new RuntimeException(error);
			}
			appProperties.load(fileInputStream);
			fileInputStream.close();
			LOGGER.info("appProperties=" + appProperties);
		} catch (IOException e) {
			LOGGER.error("cannot load app.properties", e);
            throw new RuntimeException(e);
		}
		try {
			WISE_WSDL_USER = Constants.getAppProperty("wise.wsdl.username");
		} catch (Exception e) {
			LOGGER.error("error",e);
		}
		
		try {
			WISE_WSDL_PASS = Constants.getAppProperty("wise.wsdl.password");
		} catch (Exception e) {
			 LOGGER.error("ERROR",e);
		}
	}
	
	public static String getAppProperty(String key) {
        final String property = appProperties.getProperty(key);
        if (property == null) {
            LOGGER.error("Cannot find app-property [" + key + "]");
        }
        return property;
    }
}
