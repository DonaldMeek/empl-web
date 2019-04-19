package com.meek.donald.employee;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.meek.donald.common.BaseServiceImpl;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl 
		implements EmployeeService {

	RestTemplate restTemplate;
	
	public void insertEmployee(EmployeeModel employeeModel) {
		
		String employeeUrl = super.getBaseUrl() + super.getEmployeeUri();
		ParameterizedTypeReference<HttpStatus> typeRef = 
				new ParameterizedTypeReference<HttpStatus>() {	};
		HttpEntity<EmployeeModel> requestEntity = 
				new HttpEntity<EmployeeModel>(employeeModel);
		ResponseEntity<HttpStatus> response = restTemplate.exchange(employeeUrl, 
				HttpMethod.POST,requestEntity, typeRef);
		if (!response.getStatusCode().equals(HttpStatus.OK)) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public EmployeeModel getEmployeeByExample(EmployeeModel employeeModel) {
		
		String employeeUrl = super.getBaseUrl() + super.getEmployeeUri();
		ParameterizedTypeReference<Object> typeRef = 
				new ParameterizedTypeReference<Object>() {	};
		HttpEntity<EmployeeModel> requestEntity = 
				new HttpEntity<EmployeeModel>(employeeModel);
		ResponseEntity<Object> response = restTemplate.exchange(employeeUrl, 
				HttpMethod.GET,requestEntity, typeRef);
		if (!response.getStatusCode().equals(HttpStatus.OK)) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return (EmployeeModel) response.getBody();
	}
	
	public EmployeeModel getEmployeeById(String emplId) {
		
		String employeeUrl = super.getBaseUrl() + super.getEmployeeUri();
		ParameterizedTypeReference<Object> typeRef = 
				new ParameterizedTypeReference<Object>() {	};
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(emplId);
		ResponseEntity<Object> response = restTemplate.exchange(employeeUrl, 
				HttpMethod.GET,requestEntity, typeRef);
		if (!response.getStatusCode().equals(HttpStatus.OK)) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (EmployeeModel) response.getBody();
	}
}
