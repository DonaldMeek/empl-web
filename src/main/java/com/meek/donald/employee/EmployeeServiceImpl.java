package com.meek.donald.employee;

import java.io.IOException;
import java.util.Collections;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meek.donald.common.BaseServiceImpl;
import com.meek.donald.common.SerializationUtil;
import com.meek.donald.model.employee.EmployeeModel;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl 
		implements EmployeeService {

	RestTemplate restTemplate;
	
	public void insertEmployee(EmployeeModel employeeModel) throws JsonProcessingException {
		
		String employeeUrl = super.getBaseUrl() + super.getEmployeeUri();
		ParameterizedTypeReference<HttpStatus> typeRef = 
				new ParameterizedTypeReference<HttpStatus>() {	};
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(SerializationUtil.getJson(employeeModel), headers);
		restTemplate = new RestTemplate();
		ResponseEntity<HttpStatus> response = restTemplate.exchange(
				employeeUrl, HttpMethod.POST,requestEntity, typeRef);
		if (response != null && !response.getStatusCode().equals(HttpStatus.OK)) {
			throw new HttpServerErrorException(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public EmployeeModel getEmployeeByExample(EmployeeModel employeeModel) throws IOException {
		
		String employeeUrl = super.getBaseUrl() + super.getEmplIdUri();
		String request = SerializationUtil.getJson(employeeModel);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(request, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				employeeUrl, HttpMethod.POST, requestEntity, String.class);
		if (response == null || 
				!response.getStatusCode().equals(HttpStatus.OK)) {
			throw new HttpServerErrorException(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (EmployeeModel) SerializationUtil.getBean(
				response.getBody(),EmployeeModel.class);
	}
	
	public EmployeeModel getEmployeeById(EmployeeModel emplModel) throws IOException {
		
		String employeeUrl = super.getBaseUrl() + super.getEmplIdUri();
		String request = SerializationUtil.getJson(emplModel);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(request, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				employeeUrl, HttpMethod.POST, requestEntity, String.class);
		if (response == null || 
				!response.getStatusCode().equals(HttpStatus.OK)) {
			throw new HttpServerErrorException(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (EmployeeModel) SerializationUtil.getBean(
				response.getBody(),EmployeeModel.class);
	}
}
