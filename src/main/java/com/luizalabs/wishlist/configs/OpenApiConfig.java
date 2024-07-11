package com.luizalabs.wishlist.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Luiza API Wishlist")
            .version("v1") // Versão da sua API
            .description("API para gerenciar a lista de desejos dos clientes.")
            .termsOfService("https://github.com/BrunoRMello")
            .license(new License().name("Apache 2.0").url("https://github.com/BrunoRMello")));
  }
}