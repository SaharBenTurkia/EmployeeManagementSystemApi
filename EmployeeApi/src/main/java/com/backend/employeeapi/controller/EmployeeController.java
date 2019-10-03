package com.backend.employeeapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.employeeapi.exception.ResourceNotFoundException;
import com.backend.employeeapi.model.Employee;
import com.backend.employeeapi.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/employeeapi")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
		@GetMapping("/employeeList")
		public List<Employee> getAllEmployees() {
			return employeeService.getAllEmployees();
		}
		@RequestMapping("/employee/{id}")
		public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") 
		Long employeeId)
				throws ResourceNotFoundException {
			Employee employee = employeeService.findById(employeeId)
					.orElseThrow(() -> new ResourceNotFoundException
							("Employee not found for this id :: " + employeeId));
			return ResponseEntity.ok().body(employee);
		}
		
		@PostMapping("/employeeList")
		public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
		
		employeeService.save(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(employee);
		}
		
		@PutMapping("/employee/{id}")
		public Employee updateEmployee(@PathVariable(value = "id") 
		Long employeeId, @Valid @RequestBody Employee employeeDetails) 
				throws ResourceNotFoundException {
			Employee employee = employeeService.findById(employeeId)
					.orElseThrow(() -> new ResourceNotFoundException
							("Employee not found for this id :: " + employeeId));
			employee.setEmailId(employeeDetails.getEmailId());
	        employee.setLastName(employeeDetails.getLastName());
	        employee.setFirstName(employeeDetails.getFirstName());
	        final Employee updatedEmployee = employeeService.save(employee);
			return employeeService.save(updatedEmployee);
		}
		
		@DeleteMapping("/employee/{id}")
		public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") 
		Long employeeId) 
				throws ResourceNotFoundException {
			Employee employee = employeeService.findById(employeeId)
					.orElseThrow(() -> new ResourceNotFoundException
							("Employee not found for this id :: " + employeeId));
			employeeService.delete(employee);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.accepted().body(null);
		}
	}
