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


package io.agilehandy.api.contracts;

import io.agilehandy.api.Card;
import io.agilehandy.api.CardsController;
import io.agilehandy.api.CardsDelegate;
import io.agilehandy.api.CardsRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

/**
 * @author Haytham Mohamed
 **/

@RunWith(SpringRunner.class)
@WebMvcTest
public class CardBase {

	@MockBean
	CardsDelegate delegate;

	@MockBean
	CardsRepository repository;

	@Before
	public void setup() {
		DecimalFormat df = new DecimalFormat( "#.00" );

		Card card = new Card();
		card.setId(1);
		card.setNumber("4409 3350 3050 2136");
		card.setHolderName("Sam");
		card.setMax(df.format(new Double(1500)));
		card.setBalance(df.format(new Double(1000)));
		card.setExpMonth("10");
		card.setExpYear("2020");
		card.setCode("333");

		Mockito.when(repository.findById(any())).thenReturn(Optional.of(card));
		Mockito.when(delegate.byId(any())).thenReturn(card);

		RestAssuredMockMvc.standaloneSetup(new CardsController(delegate));
	}

}
