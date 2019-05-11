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
import com.meek.donald.model.employee.Employee;
import com.meek.donald.model.employee.EmployeeModel;
import com.meek.donald.model.employee.EmployeeTransformer;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl 
		implements EmployeeService {

	RestTemplate restTemplate;
	
	public void insertEmployee(EmployeeModel employeeModel) 
			throws JsonProcessingException {
		
		String employeeUrl = super.getBaseUrl() + super.getEmployeeUri();
		ParameterizedTypeReference<HttpStatus> typeRef = 
				new ParameterizedTypeReference<HttpStatus>() {	};
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(SerializationUtil.getJson(
							EmployeeTransformer.transformEmployeeModel(employeeModel)), 
						headers);
		restTemplate = new RestTemplate();
		ResponseEntity<HttpStatus> serviceResponse = restTemplate.exchange(
				employeeUrl, HttpMethod.POST,requestEntity, typeRef);
		if (serviceResponse == null || 
				!serviceResponse.getStatusCode().equals(HttpStatus.OK)) {
			throw new HttpServerErrorException(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public EmployeeModel getEmployeeById(Integer emplId) throws IOException {
		Employee empl = new Employee();
		EmployeeModel emplModel = null;
		empl.setEmplid(emplId);
		emplModel = getEmployeeById(empl);
		return emplModel;
	}
	
	public EmployeeModel getEmployeeByExample(EmployeeModel employeeModel) 
			throws IOException {
		
		String employeeUrl = super.getBaseUrl() + super.getEmplIdUri();
		String request = SerializationUtil.getJson(employeeModel);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(request, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				employeeUrl, HttpMethod.POST, requestEntity, String.class);
		super.validateServiceResponse(response);
		return (EmployeeModel) SerializationUtil.getBean(
				response.getBody(),EmployeeModel.class);
	}
	
	public EmployeeModel getEmployeeById(Employee empl) throws IOException {
		Employee emplResponse;
		String employeeUrl = super.getBaseUrl() + super.getEmplIdUri();
		String request = SerializationUtil.getJson(empl);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(request, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				employeeUrl, HttpMethod.POST, requestEntity, String.class);
		super.validateServiceResponse(response);
		emplResponse = (Employee) SerializationUtil.getBean(
				response.getBody(),Employee.class);
		return EmployeeTransformer.transformEmployee(emplResponse);
	}

}
