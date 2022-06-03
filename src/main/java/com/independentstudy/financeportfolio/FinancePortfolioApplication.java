package com.independentstudy.financeportfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootApplication
public class FinancePortfolioApplication {

	public static void main(String[] args) {
		System.out.println("Date with timezone (EST): " + ZonedDateTime.now(ZoneId.of("US/Eastern")));
		System.out.println("Date without timezone (EST): " + LocalDate.now(ZoneId.of("US/Eastern")));
		SpringApplication.run(FinancePortfolioApplication.class, args);
	}

}
