package com.backend.employeeapi.repository;

import org.springframework.stereotype.Repository;

import com.backend.employeeapi.model.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Employee findByFirstName(String firstName);
}
