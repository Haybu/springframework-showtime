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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.*;

/**
 * @author Haytham Mohamed
 **/
@Component
public class CardsApiClient {

	private final WebClient webClient;
	private final String url;
	private final String merchant;

	public CardsApiClient(WebClient webClient
			, @Value("${services.cards.url}") String url
			, @Value("${merchant}") String merchant) {
		this.webClient = webClient;
		this.merchant = merchant;
		this.url = url;
	}

	public Card getCard(Integer id) throws CardNotFoundException {

		Card card = this.webClient
				.get()
				.uri(url + "/"+id)
				.attributes(clientRegistrationId("api-cards-view"))
				.retrieve()
				.bodyToMono(Card.class)
				.block();

		if (card == null) {
			throw new CardNotFoundException();
		}
		card.setLastTimeAccessed(new Date());
		card.setAccessedBy("user1");
		card.setMerchant(merchant);
		return card;
	}

}
