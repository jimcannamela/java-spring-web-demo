package com.galvanize.springwebdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class SpringWebDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testIndexEndpointNotFound() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isNotFound());
	}

}
