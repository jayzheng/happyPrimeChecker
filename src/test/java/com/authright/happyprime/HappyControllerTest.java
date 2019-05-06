package com.authright.happyprime;

import com.authright.happyprime.controllers.HappyController;
import com.authright.happyprime.services.RandomNumberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HappyController.class)
public class HappyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	RandomNumberService randomNumberService;


	@Test
	public void shouldReturnIsHappyPrime() throws Exception {

		mockMvc.perform(get("/v1/isHappyprime/2"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":2,\"isHappyPrime\":false}"));
		mockMvc.perform(get("/v1/isHappyprime/167"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":167,\"isHappyPrime\":true}"));
	}

	@Test
	public void shouldReturnIsHappyPrimeRandom() throws Exception {
		given(randomNumberService.getNumber()).willReturn(2L);
		mockMvc.perform(get("/v1/isHappyprime"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":2,\"isHappyPrime\":false}"));
		given(randomNumberService.getNumber()).willReturn(409L);
		mockMvc.perform(get("/v1/isHappyprime"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":409,\"isHappyPrime\":true}"));
	}


	@Test
	public void shouldReturnIsHappyPrimeAsync() throws Exception {

		mockMvc.perform(get("/v1/isHappyprimeAsync/2"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":2,\"isHappyPrime\":false}"));
		mockMvc.perform(get("/v1/isHappyprimeAsync/167"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":167,\"isHappyPrime\":true}"));
	}

	@Test
	public void shouldReturnIsHappyPrimeAsyncRandom() throws Exception {
		given(randomNumberService.getNumber()).willReturn(2L);
		mockMvc.perform(get("/v1/isHappyprimeAsync"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":2,\"isHappyPrime\":false}"));
		given(randomNumberService.getNumber()).willReturn(409L);
		mockMvc.perform(get("/v1/isHappyprimeAsync"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"number\":409,\"isHappyPrime\":true}"));
	}

	@Test
	public void shouldReturnIsHappyPrimeRandomInternalServerError() throws Exception {
		given(randomNumberService.getNumber()).willReturn(0L);
		mockMvc.perform(get("/v1/isHappyprime"))
				.andExpect(status().is5xxServerError())
				.andExpect(content().json("{\"error\":\"Error getting random number.\"}"));
	}

}
