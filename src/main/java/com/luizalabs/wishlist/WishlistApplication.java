package com.luizalabs.wishlist;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.luizalabs.wishlist.domain.models.CustomerModel;
import com.luizalabs.wishlist.domain.models.ProductModel;
import com.luizalabs.wishlist.domain.usecases.AddProductToWishlistUseCase;
import com.luizalabs.wishlist.domain.usecases.CustomerUseCase;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication

@OpenAPIDefinition(info = @Info(title = "Api Rest wishlist", description = "Api para wishlist", version = "1"))
public class WishlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishlistApplication.class, args);
	}

	@Bean
	@Profile("development")
	public CommandLineRunner mockDatabaseRunner(CustomerUseCase customerUseCase,
			AddProductToWishlistUseCase addProductToWishlistUseCase) {
		return args -> {
			List<String> ids = Arrays.asList("642bd736bac60a7a8e9668f3", "642bd737bac60a7a8e9668f5",
					"642c625f40bcbf44840993a6");
			List<String> productIds = Arrays.asList("product1", "product2", "product3");

			// for (int i = 0; i < 3; i++) {
			// customerUseCase.save(new CustomerModel(ids.get(i), new HashSet<>()));
			// }

			for (int i = 0; i < ids.size(); i++) {
				customerUseCase.save(new CustomerModel(ids.get(i), new HashSet<>()));
			}

			for (int i = 0; i < ids.size(); i++) {
				addProductToWishlistUseCase.execute(ids.get(i), new ProductModel(productIds.get(i)));
			}

		};
	}

}
