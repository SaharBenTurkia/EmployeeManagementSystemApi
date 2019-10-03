package com.backend.employeeapi;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.backend.employeeapi.model.Employee;
import com.backend.employeeapi.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class EmployeeMockTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Mock
    private EmployeeService employeeService;
	
	private static String SERVICE_URI="/employeeapi";

	private static Employee employee = new Employee();
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void createEmployeeAPI() throws Exception
	{
		employee = new Employee(0, "firstName1", "lastName1", "email1@mail.com");
		mockMvc.perform( MockMvcRequestBuilders
	      .post(SERVICE_URI+"/employeeList")
	      .content(asJsonString(employee))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}
	
	@Test
	public void getAllEmployeesAPI() throws Exception
	{
	  mockMvc.perform( MockMvcRequestBuilders
	      .get(SERVICE_URI+"/employeeList")
	      .accept(MediaType.APPLICATION_JSON))
	    //  .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
	}
	
	@Test
	public void getEmployeeByIdAPI() throws Exception
	{
	  mockMvc.perform( MockMvcRequestBuilders
	      .get(SERVICE_URI+"/employee/{id}", 16)
	      .accept(MediaType.APPLICATION_JSON))
	  //    .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(16));
	}
	
	@Test
	public void updateEmployeeAPI() throws Exception
	{
	  mockMvc.perform( MockMvcRequestBuilders
	      .put(SERVICE_URI+"/employee/{id}", 16)
	      .content(asJsonString(new Employee(16, "firstName2", "lastName2", "email2@mail.com")))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstName2"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName2"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.emailId").value("email2@mail.com"));
	}
	/*@Test
	public void deleteEmployeeAPI() throws Exception
	{
		mockMvc.perform( 
		MockMvcRequestBuilders.delete(SERVICE_URI+"/employee/{id}", 24) )
	        .andExpect(status().isAccepted());
	}*/
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
