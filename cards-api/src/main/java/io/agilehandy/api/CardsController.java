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

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Haytham Mohamed
 **/

@RestController
public class CardsController {

	private final CardsDelegate delegate;

	public CardsController(CardsDelegate delegate) {
		this.delegate = delegate;
	}

	@GetMapping("/cards")
	public Iterable<Card> getAllCards() {
		return delegate.all();
	}

	@GetMapping("/cards/{id}")
	public Card getById(@PathVariable("id") Integer id) {
		return delegate.byId(id);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleCardNotFoundException(CardNotFoundException exception) {}
}
