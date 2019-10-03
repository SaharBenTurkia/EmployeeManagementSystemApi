package com.backend.employeeapi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import com.backend.employeeapi.model.Employee;
import com.backend.employeeapi.service.EmployeeService;

@RunWith(SpringRunner.class)
public class EmployeeMockBeanTest {

	@MockBean
    private EmployeeService employeeService;
	
	@Before
	public void setUp() {
	    Employee alex = new Employee();
	    alex.setFirstName("alex");
	    Mockito.when(employeeService.findByFirstName(alex.getFirstName()))
	      .thenReturn(alex);
	}
	
	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
	    String name = "alex";
	    Employee found = employeeService.findByFirstName(name);
	  
	     assertThat(found.getFirstName())
	      .isEqualTo(name);
	 }
}
