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

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.util.Assert;

/**
 * @author Haytham Mohamed
 **/
public class ApprovedCards {

	private static final List<String> NAMES = Arrays.asList(
			"John", "Lisa", "Michael", "Linda", "Sam"
	);

	private static final List<Double> LIMITS = Arrays.asList(
			new Double(1500), new Double(2000), new Double(3000),
			new Double(5000), new Double(10000), new Double(13000),
			new Double(17000), new Double(20000)
	);

	private static Integer randRange(int min, int max) {
		return (new Random().nextInt(max) % (max - min + 1)) + min;
	}

	private static String getName() {
		return NAMES.get(new Random().nextInt(NAMES.size()));
	}

	private static Double getLimit() {
		return LIMITS.get(new Random().nextInt(LIMITS.size()));
	}

	private static String getExpMonth() {
		int month = new Random().nextInt(11) + 1;
		return String.format("%02d", month);
	}

	private static String getExpYear() {
		return String.format("%02d", randRange(2020, 2030));
	}

	private static String getCode() {
		return String.format("%03d", randRange(666, 999));
	}

	private static String getPartialNumber(int min, int max) {
		Assert.isTrue(min % 1000 > 0, "minimum number should be greater than 1000");
		Assert.isTrue(max % 1000 > 0, "minimum number should be greater than 1000");
		return String.format("%04d", randRange(min,max));
	}

	private static String format2Decimal(Double value) {
		DecimalFormat df = new DecimalFormat( "#.00" );
		return df.format(value);
	}

	public static Card createCard() {
		Card card = new Card();
		StringBuilder number = new StringBuilder(getPartialNumber(4400, 4499)).append(" ")
				.append(getPartialNumber(3300, 3399)).append(" ")
				.append(getPartialNumber(2200, 3399)).append(" ")
				.append(getPartialNumber(1100, 3399));

		Double limit = getLimit();
		Double balance = limit - randRange(100, limit.intValue());

		card.setNumber(number.toString());
		card.setHolderName(getName());
		card.setMax(format2Decimal(limit));
		card.setBalance(format2Decimal(balance));
		card.setCode(getCode());
		card.setExpMonth(getExpMonth());
		card.setExpYear(getExpYear());
		return card;
	}

}
