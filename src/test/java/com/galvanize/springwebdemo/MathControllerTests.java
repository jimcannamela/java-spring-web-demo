package com.galvanize.springwebdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class MathControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void math_Pi_Route_Should_Return_Pi() throws Exception {
		mockMvc.perform(get("/math/pi"))
				.andExpect(status().isOk())
				.andExpect(content().string("3.141592653589793"));
	}

	@Test
	public void math_Fibonacci_Should_Return_FibonacciSequence_to_34() throws Exception {
		mockMvc.perform(post("/math/fibonacci"))
				.andExpect(status().isOk())
				.andExpect(content().string("1, 1, 2, 3, 5, 8, 13, 21, 34"));
	}

}
