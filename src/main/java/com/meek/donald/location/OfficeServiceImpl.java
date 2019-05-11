package com.meek.donald.location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meek.donald.common.BaseServiceImpl;
import com.meek.donald.common.SerializationUtil;
import com.meek.donald.model.employee.EmployeeModel;
import com.meek.donald.model.employee.ManagerModel;
import com.meek.donald.model.location.LocationModel;
import com.meek.donald.model.location.OfficeModel;

public class OfficeServiceImpl extends BaseServiceImpl 
		implements OfficeService {

	public List<OfficeModel> getOfficesByLocationId(LocationModel locationModel) 
			throws IOException {
		LocationModel locationOfOffices;
		List<OfficeModel> offices;
		String officeLocIdUrl = super.getBaseUrl() + super.getOfficeLocIdUri();
		String officeByLocIdRequest = SerializationUtil.getJson(locationModel);
		HttpHeaders headers = new HttpHeaders();
		headers = super.getDefaultHttpRequestHeaders();
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(officeByLocIdRequest, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				officeLocIdUrl, HttpMethod.POST, requestEntity, String.class);
		validateServiceResponse(response);
		locationOfOffices = (LocationModel) SerializationUtil.getBean(
				response.getBody(),LocationModel.class);
		offices = locationOfOffices.getLocationOffices();
		return offices;
	}
	
	public List<OfficeModel> getOfficesByLocationExample(LocationModel locationModel) 
			throws IOException {
		List<OfficeModel> offices;
		LocationModel locationResult;
		String officeLocExampleUrl = super.getBaseUrl() + 
				super.getOfficeLocExampleUri();
		String officeByLocIdRequest = SerializationUtil.getJson(locationModel);
		HttpHeaders headers = new HttpHeaders();
		headers = super.getDefaultHttpRequestHeaders();
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(officeByLocIdRequest, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				officeLocExampleUrl, HttpMethod.POST, requestEntity, String.class);
		validateServiceResponse(response);
		locationResult = (LocationModel) SerializationUtil.getBean(
				response.getBody(), LocationModel.class);
		offices = locationResult.getLocationOffices();
		return offices;
	}
	
	public Map<Integer, EmployeeModel> getOfficeEmployeesByManagerid(
			ManagerModel manager) throws IOException {
		Map<Integer, EmployeeModel> officeEmplsByManagerId;
		OfficeModel officeModel;
		String officeEmplByManagerIdUrl = 
				super.getBaseUrl() + super.getOfficeEmplByManagerIdUri();
		String officeEmplMgrIdRequest = SerializationUtil.getJson(manager);
		HttpHeaders headers = new HttpHeaders();
		headers = super.getDefaultHttpRequestHeaders();
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(officeEmplMgrIdRequest, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				officeEmplByManagerIdUrl, HttpMethod.POST, requestEntity, String.class);
		validateServiceResponse(response);
		officeModel = (OfficeModel) SerializationUtil.getBean(
				response.getBody(),OfficeModel.class);
		officeEmplsByManagerId = officeModel.getOfficeEmployeesIndexedByManagerId();
		return officeEmplsByManagerId;
	}
	
	public List<EmployeeModel> getEmployeesByOfficeId(OfficeModel officeModel) 
			throws IOException {
		List<EmployeeModel> emplList = new ArrayList<EmployeeModel>();
		String employeeOfficeIdUrl = super.getBaseUrl() + super.getEmplOfficeIdUri();
		String emplOfficeIdRequest = SerializationUtil.getJson(officeModel);
		HttpHeaders headers = new HttpHeaders();
		headers = super.getDefaultHttpRequestHeaders();
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(emplOfficeIdRequest, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				employeeOfficeIdUrl, HttpMethod.POST, requestEntity, String.class);
		validateServiceResponse(response);
		officeModel = (OfficeModel) SerializationUtil.getBean(
				response.getBody(),OfficeModel.class);
		emplList = officeModel.getOfficeEmployees();
		return emplList;
	}
	
	public OfficeModel getOfficeByEmplId(EmployeeModel emplModel) 
			throws IOException {
		OfficeModel officeModel;
		String officeEmployeeIdUrl = super.getBaseUrl() + super.getOfficeEmplIdUri();
		String officeEmplIdRequest = SerializationUtil.getJson(emplModel);
		HttpHeaders headers = new HttpHeaders();
		headers = super.getDefaultHttpRequestHeaders();
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(officeEmplIdRequest, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				officeEmployeeIdUrl, HttpMethod.POST, requestEntity, String.class);
		validateServiceResponse(response);
		officeModel = (OfficeModel) SerializationUtil.getBean(
				response.getBody(),OfficeModel.class);
		return officeModel;
	}

}
