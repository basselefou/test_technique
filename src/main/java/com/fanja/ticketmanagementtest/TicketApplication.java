package com.fanja.ticketmanagementtest;

import com.fanja.ticketmanagementtest.dtos.UserDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TicketApplication {



	public static void main(String[] args) {
		SpringApplication.run(TicketApplication.class, args);

		List<UserDto> dtos = new ArrayList<>();

	}

}
