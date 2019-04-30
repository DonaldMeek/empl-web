package com.meek.donald.employee;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meek.donald.model.employee.EmployeeModel;

public interface EmployeeService{
	public void insertEmployee(EmployeeModel employeeModel) 
			throws JsonProcessingException;
	public EmployeeModel getEmployeeByExample(EmployeeModel employeeModel) throws JsonProcessingException, IOException;
	public EmployeeModel getEmployeeById(EmployeeModel employeeModel) throws JsonProcessingException, IOException;
}
