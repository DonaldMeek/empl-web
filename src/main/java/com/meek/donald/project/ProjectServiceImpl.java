package com.meek.donald.project;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.meek.donald.common.BaseServiceImpl;
import com.meek.donald.common.SerializationUtil;
import com.meek.donald.location.OfficeService;
import com.meek.donald.location.OfficeServiceImpl;
import com.meek.donald.model.location.OfficeModel;
import com.meek.donald.model.projects.Project;
import com.meek.donald.model.projects.ProjectModel;

@Service
public class ProjectServiceImpl extends BaseServiceImpl 
			implements ProjectService {

	@Autowired
	OfficeService officeService;
	
	public List<Integer> getEmployeeIdsByProjectExample(ProjectModel projModel)
			throws IOException {
		List<Integer> emplIds;
		ProjectModel project;
		String projectEmplIdsUrl = super.getBaseUrl() + 
				super.getProjectEmplIdsUri();
		String projRequestModel = SerializationUtil.getJson(projModel);
		HttpHeaders headers = new HttpHeaders();
		headers = super.getDefaultHttpRequestHeaders();
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(projRequestModel, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				projectEmplIdsUrl, HttpMethod.POST, requestEntity, String.class);
		super.validateServiceResponse(response);
		project = (ProjectModel) SerializationUtil.getBean(
				response.getBody(),ProjectModel.class);
		emplIds = project.getProjectEmplIds();
		return emplIds;
	}
	
	public OfficeModel getOfficeByProjectExample(ProjectModel projModel)
			throws IOException {
		ProjectModel officeProj = getProjectByExample(projModel);
		OfficeModel office = officeService.getOfficeById(officeProj.getOfficeId());
		return office;
	}
	
	public ProjectModel getProjectByExample(ProjectModel projModel) 
			throws IOException {
		ProjectModel project;
		String projectExampleUrl = super.getBaseUrl() + 
				super.getProjectExampleUri();
		String projExampleRequest = SerializationUtil.getJson(projModel);
		HttpHeaders headers = new HttpHeaders();
		headers = super.getDefaultHttpRequestHeaders();
		HttpEntity<String> requestEntity = 
				new HttpEntity<String>(projExampleRequest, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(
				projectExampleUrl, HttpMethod.POST, requestEntity, 
				String.class);
		super.validateServiceResponse(response);
		project = (ProjectModel) SerializationUtil.getBean(
				response.getBody(),ProjectModel.class);
		return project;
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
		super.validateServiceResponse(response);
		return (ProjectModel) SerializationUtil.getBean(
				response.getBody(),ProjectModel.class);
	}
}
