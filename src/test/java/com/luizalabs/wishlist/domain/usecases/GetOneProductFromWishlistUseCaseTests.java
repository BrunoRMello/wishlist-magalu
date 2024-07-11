package com.luizalabs.wishlist.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//import org.h2.command.dml.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.luizalabs.wishlist.domain.ports.CustomerPort;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class GetOneProductFromWishlistUseCaseTests {

  @Autowired
  private GetOneProductFromWishlistUseCase getOneProductFromWishlistUseCase;

  @MockBean
  private CustomerPort customerPort;

  @Test
  void shouldReturnProductExistsInWishlist() {
    String customerId = "customer1";
    String productId = "product2";

    // Simula o comportamento do customerPort
    when(customerPort.existsByCustomerIdAndProductId(customerId, productId)).thenReturn(true);

    // Executa o caso de uso
    ResponseEntity<String> response = getOneProductFromWishlistUseCase.execute(customerId, productId);

    // Verifica se o produto está na wishlist
    assertEquals(HttpStatus.OK, response.getStatusCode(), "O status do retorno não é o esperado.");
    assertEquals("Produto está na lista de desejos", response.getBody(), "A mensagem do retorno não é a esperada.");
  }

  @Test
  void shouldReturnProductNotInWishlist() {
    String customerId = "customer1";
    String productId = "nonExistentProduct";

    // Simula o comportamento do customerPort
    when(customerPort.existsByCustomerIdAndProductId(customerId, productId)).thenReturn(false);

    // Executa o caso de uso
    ResponseEntity<String> response = getOneProductFromWishlistUseCase.execute(customerId, productId);

    // Verifica se o produto não está na wishlist
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "O status do retorno não é o esperado.");
    assertEquals("Produto não está na lista de desejos", response.getBody(), "A mensagem do retorno não é a esperada.");
  }
}
