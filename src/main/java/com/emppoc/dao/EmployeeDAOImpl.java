package com.emppoc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.emppoc.model.Employee;

@Service

public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveOrUpdate(Employee employee) {
		if (employee.getId() > 0) {
			// update

			String query = "UPDATE employees SET first_name=?, last_name=?, email=?, phone=?, job_title=?, active =? WHERE employee_id=?";
			jdbcTemplate.update(query, employee.getFirstName(), employee.getLastName(), employee.getEmail(),
					employee.getPhone(), employee.getJobTitle(), employee.getId(), employee.getActive());
		} else {
			// insert

			String query = "INSERT INTO employees(first_name, last_name, email, phone, job_title,active) VALUES(?, ?, ?, ?, ?, ?,?)";
			jdbcTemplate.update(query, employee.getId(), employee.getFirstName(), employee.getLastName(),
					employee.getEmail(), employee.getPhone(), employee.getJobTitle(), employee.getActive());

		}

	}

	@Override
	public void delete(int employeeId) {
		String sql = "DELETE FROM employees WHERE employee_id=?";
		jdbcTemplate.update(sql, employeeId);

	}

	@Override
	public Employee get(int employeeId) {
		String sql = "SELECT * FROM employees WHERE employee_id=" + employeeId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Employee>() {

			@Override
			public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Employee employee = new Employee();
					employee.setId(rs.getInt("employee_id"));
					employee.setFirstName(rs.getString("firstname"));
					employee.setLastName(rs.getString("lastname"));
					employee.setEmail(rs.getString("email"));
					employee.setPhone(rs.getString("phone"));
					employee.setJobTitle(rs.getString("jobTitle"));
					employee.setActive(rs.getInt("active"));
					return employee;
				}

				return null;
			}

		});
	}

	@Override
	public List<Employee> list() {
		String sql = "SELECT * FROM employees";
		List<Employee> listEmployee = jdbcTemplate.query(sql, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee aEmployee = new Employee();

				aEmployee.setId(rs.getInt("employee_id"));
				aEmployee.setFirstName(rs.getString("first_name"));
				aEmployee.setLastName(rs.getString("last_name"));
				aEmployee.setEmail(rs.getString("email"));
				aEmployee.setPhone(rs.getString("phone"));
				aEmployee.setJobTitle(rs.getString("job_title"));
				aEmployee.setActive(rs.getInt("active"));
				
				return aEmployee;
			}

		});

		return listEmployee;
	}

}
