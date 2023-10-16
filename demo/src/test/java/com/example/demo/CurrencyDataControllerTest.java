package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyDataControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void readCurrencyTest() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/currency/read/{code}", "USD");

		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").exists())
			.andExpect(jsonPath("$.code").value("USD"));

	}

	@Test
	public void createCurrencyTest() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		JSONObject request = new JSONObject().put("code", "CNY").put("description", "人民幣");

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/currency/create")
				.headers(httpHeaders)
				.content(request.toString());

		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isOk());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/currency/read/{code}", "CNY"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").exists());
		
	}
	
	@Test
	public void updateCurrencyTest() throws Exception{
		
		HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	    
		JSONObject request = new JSONObject().put("code", "EUR").put("description", "不是歐元");

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/currency/update")
				.headers(httpHeaders)
				.content(request.toString());
		
		mockMvc.perform(requestBuilder)
		.andDo(print())
		.andExpect(status().isOk());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/currency/read/{code}", "EUR"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.description").value("不是歐元"));
		
	}

	@Test
	@Rollback(true)
	public void deleteCurrencyTest() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/currency//delete/{code}","USD");

		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isOk());

		mockMvc.perform(MockMvcRequestBuilders.get("/currency/read/{code}", "USD"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").doesNotExist());

	}
	
	@Test
	public void readAllCurrenciesTest() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/currency/readAll");

		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isOk());

	}
	
}
	
	
