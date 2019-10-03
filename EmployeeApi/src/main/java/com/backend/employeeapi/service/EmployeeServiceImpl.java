package com.backend.employeeapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.employeeapi.model.Employee;
import com.backend.employeeapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository employeeDAO;

	@Override
	public Employee findByFirstName(String firstName) {
		return employeeDAO.findByFirstName(firstName);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDAO.findAll();
	}

	@Override
	public Employee save(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDAO.save(employee);
	}

	@Override
	public void delete(Employee employee) {
		// TODO Auto-generated method stub
		employeeDAO.delete(employee);
	}

	@Override
	public Optional<Employee> findById(long id) {
		// TODO Auto-generated method stub
		return employeeDAO.findById(id);
	}

	@Override
	public void saveAll(List<Employee> employees) {
		// TODO Auto-generated method stub
		employeeDAO.saveAll(employees);
	}

	@Override
	public Employee getEmployeeById(long id) {
		return employeeDAO.findById(id).get();
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeDAO.findAll();
	}
	
	
}
