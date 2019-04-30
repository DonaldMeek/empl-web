package com.meek.donald.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meek.donald.model.employee.EmployeeModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController()
public class EmployeeController {
	
	@Autowired
	EmployeeService emplService;
	
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
			return emplService.getEmployeeById(emplModel);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
}
