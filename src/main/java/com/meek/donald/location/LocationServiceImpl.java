package com.meek.donald.location;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meek.donald.common.BaseServiceImpl;
import com.meek.donald.common.SerializationUtil;
import com.meek.donald.model.employee.EmployeeModel;
import com.meek.donald.model.location.LocationModel;
import com.meek.donald.model.location.OfficeModel;

@Service
public class LocationServiceImpl extends BaseServiceImpl 
		implements LocationService {
	
	@Override
	public EmployeeModel getEmployeeByLocationExample(LocationModel locModel) 
			throws IOException {
		EmployeeModel employee;
		String emplLocIdUrl = super.getBaseUrl() + super.getEmplByLocExampleUri();
		String emplByLocRequest = SerializationUtil.getJson(locModel);
		HttpHeaders headers = new HttpHeaders();
		headers = super.getDefaultHttpRequestHeaders();
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(emplByLocRequest, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				emplLocIdUrl, HttpMethod.POST, requestEntity, String.class);
		validateServiceResponse(response);
		employee = (EmployeeModel) SerializationUtil.getBean(
				response.getBody(),EmployeeModel.class);
		return employee;
	}

	@Override
	public EmployeeModel getEmployeeByLocationId(LocationModel locModel) 
			throws IOException {
		EmployeeModel employee;
		String emplLocIdUrl = super.getBaseUrl() + super.getEmplLocIdUri();
		String emplByLocIdRequest = SerializationUtil.getJson(locModel);
		HttpHeaders headers = new HttpHeaders();
		headers = super.getDefaultHttpRequestHeaders();
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(emplByLocIdRequest, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				emplLocIdUrl, HttpMethod.POST, requestEntity, String.class);
		validateServiceResponse(response);
		employee = (EmployeeModel) SerializationUtil.getBean(
				response.getBody(),EmployeeModel.class);
		return employee;
	} 
	
	@Override
	public OfficeModel getOfficeByLocationId(LocationModel locModel) 
			throws IOException {
		OfficeModel office;
		String officeLocIdUrl = super.getBaseUrl() + super.getOfficeLocIdUri();
		String officeByLocIdRequest = SerializationUtil.getJson(locModel);
		HttpHeaders headers = new HttpHeaders();
		headers = super.getDefaultHttpRequestHeaders();
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(officeByLocIdRequest, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				officeLocIdUrl, HttpMethod.POST, requestEntity, String.class);
		validateServiceResponse(response);
		office = (OfficeModel) SerializationUtil.getBean(
				response.getBody(),OfficeModel.class);
		return office;
	} 

}
