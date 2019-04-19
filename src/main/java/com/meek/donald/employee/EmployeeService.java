package com.meek.donald.employee;

public interface EmployeeService{
	public void insertEmployee(EmployeeModel employeeModel);
	public EmployeeModel getEmployeeByExample(EmployeeModel employeeModel);
	public EmployeeModel getEmployeeById(String emplId);
}
