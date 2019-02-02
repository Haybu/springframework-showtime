package io.agilehandy.api;

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
	public CommandLineRunner clr(CardsRepository repository) {
		return args -> {
			IntStream.range(0, 10)
					.mapToObj(i -> ApprovedCards.createCard())
					.forEach(repository::save);
		};
	}
}

