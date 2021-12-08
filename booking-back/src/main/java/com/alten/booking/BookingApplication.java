package com.alten.booking;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * @author fatma DERBEL BOUATTOUR
 */
@SpringBootApplication

public class BookingApplication extends SpringBootServletInitializer {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BookingApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

}
