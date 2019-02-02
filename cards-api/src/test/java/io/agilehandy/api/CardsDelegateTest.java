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

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

/**
 * @author Haytham Mohamed
 **/
@RunWith(MockitoJUnitRunner.class)
public class CardsDelegateTest {
	@Mock
	CardsDelegate delegate;

	@Mock
	CardsRepository repository;

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
	public void getAllCards_shouldReturnAllCards() {
		Mockito.when(delegate.all()).thenReturn(Arrays.asList(card));

		Iterable<Card> cards = delegate.all();
		Assertions.assertThat(cards).isNotEmpty();

		List<Card> cardsList = new ArrayList<>();
		cards.forEach(cardsList::add);

		Assertions.assertThat(cardsList.size()).isEqualTo(1);
		Assertions.assertThat(cardsList.get(0).getCode()).isEqualTo("333");
	}

	@Test
	public void getOneCard_shouldReturnOneCard() {
		Mockito.when(delegate.byId(any())).thenReturn(card);

		Card card = delegate.byId(any());
		Assertions.assertThat(card).isNotNull();
		Assertions.assertThat(card.getCode()).isEqualTo("333");
	}

	@Test(expected = CardNotFoundException.class)
	public void getNonExistCard_shouldThrowException() {
		Mockito.when(delegate.byId(any())).thenThrow(new CardNotFoundException());
		delegate.byId(any());
	}

}
