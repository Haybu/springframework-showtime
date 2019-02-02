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

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Haytham Mohamed
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE
 , properties = {"services.cards.url=http://localhost:6565/cards"})
@AutoConfigureStubRunner(ids = {"io.agilehandy:cards-api:+:stubs:6565"},
		stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class CardsContractTests {

	@Autowired
	CardsApiClient client;

	@Test
	public void getCardById_shouldRetrieveOneCard() throws CardNotFoundException {
		Card card = client.getCard(1);
		Assertions.assertThat(card.getCode()).isEqualTo("333");
		Assertions.assertThat(card.getNumber()).isEqualTo("4409 3350 3050 2136");
		Assertions.assertThat(card.getHolderName()).isEqualTo("Sam");
	}
}
