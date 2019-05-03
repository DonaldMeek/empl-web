package com.meek.donald.project;

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
import com.meek.donald.model.location.OfficeModel;
import com.meek.donald.model.projects.ProjectModel;

@Service
public class ProjectServiceImpl extends BaseServiceImpl 
			implements ProjectService {

	public List<Integer> getEmployeeIdsByProjectExample(ProjectModel projModel)
			throws IOException {
		List<Integer> emplIds;
		ProjectModel project;
		String projectEmplIdsUrl = super.getBaseUrl() + 
				super.getProjectEmplIdsUri();
		String officeByLocIdRequest = SerializationUtil.getJson(projModel);
		HttpHeaders headers = new HttpHeaders();
		headers = super.getDefaultHttpRequestHeaders();
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(officeByLocIdRequest, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				projectEmplIdsUrl, HttpMethod.POST, requestEntity, String.class);
		if (response == null || 
				!response.getStatusCode().equals(HttpStatus.OK)) {
			throw new HttpServerErrorException(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		project = (ProjectModel) SerializationUtil.getBean(
				response.getBody(),ProjectModel.class);
		emplIds = project.getProjectEmplIds();
		return emplIds;
	}
	public OfficeModel getOfficeByProjectExample(ProjectModel projModel) 
			throws IOException {
		OfficeModel office;
		String officeByProjectExampleUrl = super.getBaseUrl() + 
				super.getOfficeByProjectExampleUri();
		String officeByLocIdRequest = SerializationUtil.getJson(projModel);
		HttpHeaders headers = new HttpHeaders();
		headers = super.getDefaultHttpRequestHeaders();
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(officeByLocIdRequest, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				officeByProjectExampleUrl, HttpMethod.POST, requestEntity, 
				String.class);
		if (response == null || 
				!response.getStatusCode().equals(HttpStatus.OK)) {
			throw new HttpServerErrorException(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		office = (OfficeModel) SerializationUtil.getBean(
				response.getBody(),OfficeModel.class);
		return office;
	}
	public ProjectModel getAllActiveProjects() 
			throws IOException {
		String allActiveProjectUrl = super.getBaseUrl() + 
				super.getAllActiveProjectUri();
		HttpHeaders headers = new HttpHeaders();
		headers = super.getDefaultHttpRequestHeaders();
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				allActiveProjectUrl, HttpMethod.POST, requestEntity, 
				String.class);
		if (response == null || 
				!response.getStatusCode().equals(HttpStatus.OK)) {
			throw new HttpServerErrorException(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (ProjectModel) SerializationUtil.getBean(
				response.getBody(),ProjectModel.class);
	}
}
