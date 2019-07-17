package com.emppoc.service;

import java.util.List;

import com.emppoc.model.Employee;

public interface EmployeeService {
	
public void saveOrUpdate(Employee employee);
	
	public void delete(int employeeId);
	
	public Employee get(int employeeId);
	
	public List<Employee> list();

}
