package io.agilehandy.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	public CommandLineRunner clr(CardsRepository repository
			, @Value("${currency}") String currency) {
		return args -> {
			IntStream.range(0, 10)
					.mapToObj(i -> {
						Card card = ApprovedCards.createCard();
						card.setCurrency(currency);
						return card;
					})
					.forEach(repository::save);
		};
	}
}

