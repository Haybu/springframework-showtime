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

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author Haytham Mohamed
 **/
@Data
@NoArgsConstructor
@ToString
public class Card {
	private Integer id;

	private String number;
	private String holderName;
	private String expMonth;
	private String expYear;
	private String code;
	private String max;
	private String balance;
	private String currency;

	private Date LastTimeAccessed;
	private String accessedBy;
	private String merchant;
}
