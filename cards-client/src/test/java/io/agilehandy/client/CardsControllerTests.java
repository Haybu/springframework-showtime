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


package io.agilehandy.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Haytham Mohamed
 **/
@RunWith(SpringRunner.class)
@WebMvcTest
public class CardsControllerTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CardsApiClient client;

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
	public void getCardById_shouldRetrieveOneCard() throws Exception, CardNotFoundException {
		when(client.getCard(any())).thenReturn(card);
		mockMvc.perform(MockMvcRequestBuilders.get("/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("number").value("1111 1111 1111 1111"))
				.andExpect(jsonPath("holderName").value("John"))
				.andExpect(jsonPath("code").value("333"))
		;
	}

	@Test
	public void getInvalidCardById_shouldThrowException() throws Exception, CardNotFoundException {
		when(client.getCard(any())).thenThrow(new CardNotFoundException());
		mockMvc.perform(MockMvcRequestBuilders.get("/1"))
				.andExpect(status().isNotFound())
		;
	}
}
