package com.tomato.orders;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Order Service REST APIs",
				description = "Order Service REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Joe",
						email = "joerey137@gmail.com",
						url = "https://www.linkedin.com/in/joe-raymond-93210427a/"
				)
		)
)
public class OrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}

}
