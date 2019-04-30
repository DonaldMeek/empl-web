package com.meek.donald.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.meek.donald.employee.EmployeeModel;

@RestController()
public class EmployeeController {
	
	@Autowired
	EmployeeService emplService;
	
	@PostMapping("/employee")
	public void insertEmployee(@RequestBody EmployeeModel emplModel) {
		emplService.insertEmployee(emplModel);
	}
}
