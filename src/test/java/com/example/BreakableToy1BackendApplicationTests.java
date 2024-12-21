package com.example;

import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.print.attribute.standard.Media;

@SpringBootTest
@AutoConfigureMockMvc
class BreakableToy1BackendApplicationTests {

	@Autowired
	private MockMvcTester mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void callingProductsGetAListOfAllTheProductsInTheInventoryThorws200ok() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/products"))
//				.andExpect
	}

}
