package com.meek.donald.common;

public abstract class BaseService {

	protected Boolean propertiesLoaded;
	protected String baseUrl;
	protected String employeeUri;
	protected String emplIdUri;
	
	abstract void loadUrlProperties();
}
