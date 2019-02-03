/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.agilehandy.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Haytham Mohamed
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {CardsController.class})
@TestPropertySource(properties = {
		"eureka.client.register-with-eureka=false",
		"eureka.client.fetch-registry=false",
		"spring.cloud.config.enabled=false",
		"currency=US-DOLLAR"
})
@ActiveProfiles("test")
public class CardsControllerTests {

	@MockBean
	CardsRepository repository;

	@MockBean
	CardsDelegate delegate;

	@Autowired
	MockMvc mockMvc;

	Card card = null;

	@Before
	public void setup() {
		card = new Card();
		card.setId(10);
		card.setNumber("1111 1111 1111 1111");
		card.setHolderName("John");
		card.setMax(new Double(1500).toString());
		card.setBalance(new Double(1000).toString());
		card.setExpMonth("10");
		card.setExpYear("2020");
		card.setCode("333");
	}

	@Test
	@WithMockUser
	public void getAllCards_shouldRetrieveAllCards() throws Exception {
		Mockito.when(repository.findAll()).thenReturn(Arrays.asList(card));
		Mockito.when(delegate.all()).thenReturn(Arrays.asList(card));
		mockMvc.perform(MockMvcRequestBuilders.get("/cards"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("@.[0].number").value("1111 1111 1111 1111"))
				.andExpect(jsonPath("@.[0].holderName").value("John"))
				.andExpect(jsonPath("@.[0].code").value("333"))
				;
	}

	@Test
	@WithMockUser
	public void getOneCard_shouldRetrieveOneCard() throws Exception {
		Mockito.when(repository.findById(any())).thenReturn(Optional.of(card));
		Mockito.when(delegate.byId(any())).thenReturn(card);
		mockMvc.perform(MockMvcRequestBuilders.get("/cards/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("number").value("1111 1111 1111 1111"))
				.andExpect(jsonPath("holderName").value("John"))
				.andExpect(jsonPath("code").value("333"))
		;
	}

	@Test
	@WithMockUser
	public void getNonExistingCard_shouldThrowException() throws Exception {
		Mockito.when(repository.findById(any())).thenReturn(null);
		Mockito.when(delegate.byId(any())).thenThrow(new CardNotFoundException());

		mockMvc.perform(MockMvcRequestBuilders.get("/cards/1"))
				.andExpect(status().isNotFound());
	}
}
