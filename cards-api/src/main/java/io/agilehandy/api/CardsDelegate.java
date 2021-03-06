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

import org.springframework.stereotype.Service;

/**
 * @author Haytham Mohamed
 **/

@Service
public class CardsDelegate {

	private final CardsRepository cardRepository;

	public CardsDelegate(CardsRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	public Iterable<Card> all() {
		return cardRepository.findAll();
	}

	public Card byId(Integer id) {
		Card card = cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException());
		if (card == null) {
			throw new CardNotFoundException();
		}
		return card;
	}
}
