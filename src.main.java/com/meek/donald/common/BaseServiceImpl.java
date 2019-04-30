package com.meek.donald.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class BaseServiceImpl extends BaseService {

	
	public BaseServiceImpl() {
		if ((super.propertiesLoaded == null) || (super.propertiesLoaded.booleanValue() == false))
		{ 
			loadUrlProperties(); 
			propertiesLoaded = new Boolean(true);
		}
	}
	
	public void loadUrlProperties() {
		InputStream inputPropertyStream = null;
		try {
			inputPropertyStream = getClass().getResourceAsStream("/service_url.properties");
			Properties uriProp = new Properties();
			uriProp.load(inputPropertyStream);
			super.baseUrl = uriProp.getProperty("service.url.host")
					+ ":" + uriProp.getProperty("service.url.port");
			setEmployeeUri(uriProp);
			setEmplIdUri(uriProp);
			inputPropertyStream.close();
		}
		catch(IOException ioe) {
			System.out.println("Failed to load properties file");
		}
	}
	
	private void setEmployeeUri(Properties appUriProp) {
		super.employeeUri = appUriProp.getProperty("service.url.employee");
	}
	
	private void setEmplIdUri(Properties appUriProp) {
		super.emplIdUri = appUriProp.getProperty("service.url.employee.id");
	}
	
	public boolean isPropertiesLoaded() {
		return super.propertiesLoaded.booleanValue();
	}
	
	public String getBaseUrl() {
		return super.baseUrl;
	}
	
	public String getEmployeeUri() {
		return super.employeeUri;
	}
}