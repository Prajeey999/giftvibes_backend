package com.giftvibes.prajeetkumar;

import com.giftvibes.prajeetkumar.Models.Category;
import com.giftvibes.prajeetkumar.Models.Products;
import com.giftvibes.prajeetkumar.Repository.ProductsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.giftvibes.prajeetkumar.Service.AuthenticationService;
import com.giftvibes.prajeetkumar.dto.request.RegisterRequest;

@SpringBootApplication
public class PrajeetkumarApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrajeetkumarApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService authenticationService) {
		return args -> {
			var admin = RegisterRequest.builder()
					.name("Admin")
					.email("admin@gmail.com").password("Admin@123")
					.role("admin")
					.build();
			System.out.println("Admin Registration: " + authenticationService.register(admin).getMessage());

			var user = RegisterRequest.builder()
					.name("John")
					.email("john@gmail.com").password("John@123")
					.role("user")
					.build();
			System.out.println("User Registration: " + authenticationService.register(user).getMessage());
		};
	}

}
