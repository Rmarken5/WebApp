package com.util;

import java.io.FileInputStream;
import java.util.Properties;

public class ApplicationProperties {
	Properties properties = null;
	
	public ApplicationProperties()throws Exception{
		this.properties = new Properties();
		FileInputStream input = new FileInputStream("../webapp/WEB-INF/resources/properties/application.properties");
		this.properties.load(this.getClass().getResourceAsStream("/HelloWorld/src/main/webapp/WEB-INF/resources/properties/application.properties"));
		}
	public static ApplicationProperties getInstance() throws Exception{
		return new ApplicationProperties();
	}
	public String getProperty(String propertyName) throws Exception{
		try{
			if(null != propertyName){
				return  this.properties.getProperty(propertyName);
			}
			return null;
		}catch(Exception io){
			throw io;
		}
	}
}
