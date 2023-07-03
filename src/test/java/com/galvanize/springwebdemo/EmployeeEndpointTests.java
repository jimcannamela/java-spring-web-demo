package com.galvanize.springwebdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class EmployeeEndpointTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void employeeWithId_returns_Employee() throws Exception {
		mockMvc.perform(get("/api/employee/3"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.firstName").value("Third"))
				.andExpect(jsonPath("$.lastName").value("Employee"));
	}

	@Test
	void employeeWithFirstNameAndLastNameParameters_returns_EmployeeWithinArray() throws Exception {
		mockMvc.perform(get("/api/employee/search")
						.param("firstName", "Fifth")
						.param("lastName", "Employee"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(5))
				.andExpect(jsonPath("$[0].firstName").value("Fifth"))
				.andExpect(jsonPath("$[0].lastName").value("Employee"));
	}

	@Test
	void employeeAdd_savesNewEmployee() throws Exception {
		String json = "{ \"id\": \"6\", \"firstName\": \"Sixth\", \"lastName\": \"Employee\" }";
		mockMvc.perform(post("/api/employee/add")
						.contentType(MediaType.APPLICATION_JSON).content(json))
				.andDo(print())
				.andExpect(status().isCreated());
		mockMvc.perform(get("/api/employee/6"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(6))
				.andExpect(jsonPath("$.firstName").value("Sixth"))
				.andExpect(jsonPath("$.lastName").value("Employee"));
	}
}
