package com.backend.employeeapi.service;

import java.util.List;
import java.util.Optional;

import com.backend.employeeapi.model.Employee;

public interface EmployeeService {
	
	public Optional<Employee> findById(long id);
	
	public Employee findByFirstName(String firstName);
	
	public List<Employee> getAllEmployees();
	
	public Employee save(Employee employee);
	
	public void delete(Employee employee);
	
	public void saveAll(List<Employee> employees);
	
	public Employee getEmployeeById(long id);
	
	public List<Employee> findAll();

}
