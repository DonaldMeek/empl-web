package com.meek.donald.employee;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.meek.donald.model.employee.Employee;
import com.meek.donald.model.employee.EmployeeModel;
import com.meek.donald.model.location.LocationModel;

public interface EmployeeService{
	void insertEmployee(EmployeeModel employeeModel) 
			throws JsonProcessingException;
	EmployeeModel getEmployeeByExample(EmployeeModel employeeModel) 
			throws IOException;
	EmployeeModel getEmployeeById(Employee employee) 
			throws IOException;
	EmployeeModel getEmployeeById(Integer emplId) 
			throws IOException;
}
