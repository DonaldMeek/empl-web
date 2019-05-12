package com.meek.donald.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.meek.donald.model.projects.ProjectModel;

@RestController
public class ProjectController {

	@Autowired 
	ProjectService projectService;
	
	@PostMapping(value="/project/example", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ProjectModel getProjectByExample(@RequestBody ProjectModel proj) {		
		try {
			return projectService.getProjectByExample(proj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	@PostMapping(value="/project/all", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ProjectModel getAllActiveProjects() {		
		try {
			return projectService.getAllActiveProjects();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
