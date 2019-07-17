package com.emppoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emppoc.dao.EmployeeDAOImpl;
import com.emppoc.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAOImpl employeeDao;

	@Override
	public void saveOrUpdate(Employee employee) {
		employeeDao.saveOrUpdate(employee);

	}

	@Override
	public void delete(int employeeId) {
		employeeDao.delete(employeeId);

	}

	@Override
	public Employee get(int employeeId) {
		return employeeDao.get(employeeId);
	}

	@Override
	public List<Employee> list() {
		return employeeDao.list();
	}

}
