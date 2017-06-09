package com.nju.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CinemaTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaTicketApplication.class, args);
	}
}
