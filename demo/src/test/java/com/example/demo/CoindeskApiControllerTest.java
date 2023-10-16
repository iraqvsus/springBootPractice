package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class CoindeskApiControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void callCoindeskApiTest() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/coindesk/ori");

		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isOk());
		
	}

	@Test
	public void changeValueOfCoindeskApi() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/coindesk/new");

		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isOk());

	}

}
