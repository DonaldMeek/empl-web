package com.meek.donald.employee;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meek.donald.location.LocationService;
import com.meek.donald.location.OfficeService;
import com.meek.donald.model.employee.EmployeeModel;
import com.meek.donald.model.employee.EmployeeTransformer;
import com.meek.donald.model.location.LocationModel;
import com.meek.donald.model.location.OfficeModel;
import com.meek.donald.model.projects.ProjectModel;
import com.meek.donald.project.ProjectService;

@RestController()
public class EmployeeController {
	
	@Autowired
	EmployeeService emplService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	OfficeService officeService;
	
	@Autowired 
	ProjectService projectService;
	
	@PostMapping(value="/employee", 
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public void insertEmployee(@RequestBody EmployeeModel emplModel) {
		try {
			emplService.insertEmployee(emplModel);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping(value="/employee/example", consumes=MediaType.APPLICATION_JSON_VALUE)
	public EmployeeModel getEmployeeByExample(@RequestBody EmployeeModel employeeModel) {		
		try {
			return emplService.getEmployeeByExample(employeeModel);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	@PostMapping(value="/employee/id", consumes=MediaType.APPLICATION_JSON_VALUE)
	public EmployeeModel getEmployeeById(@RequestBody EmployeeModel emplModel) {		
		try {
			return emplService.getEmployeeById(
					EmployeeTransformer.transformEmployeeModel(emplModel));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	@PostMapping(value="/employee/loc", consumes=MediaType.APPLICATION_JSON_VALUE)
	public EmployeeModel getEmployeesByLocation(@RequestBody LocationModel locModel) {		
		try {
			if (locModel.getLocationId() != null)
				return locationService.getEmployeeByLocationId(locModel);
			else return locationService.getEmployeeByLocationExample(locModel);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping(value="/employee/office", consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<EmployeeModel> getEmployeesByOffice(@RequestBody OfficeModel officeModel) {		
		try {
			return officeService.getEmployeesByOfficeId(officeModel);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@PostMapping(value="/employee/project", consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<EmployeeModel> getEmployeesByProject(@RequestBody ProjectModel projModel) {		
		try {
			List<EmployeeModel> employees = null;
			List<Integer> emplIds = 
					projectService.getEmployeeIdsByProjectExample(projModel);
			for (Integer employeeId : emplIds) {
				emplService.getEmployeeById(employeeId);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
