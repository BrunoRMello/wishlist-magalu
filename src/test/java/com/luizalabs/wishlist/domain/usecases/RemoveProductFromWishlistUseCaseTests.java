package com.luizalabs.wishlist.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.luizalabs.wishlist.domain.models.CustomerModel;
import com.luizalabs.wishlist.domain.models.Product;
import com.luizalabs.wishlist.domain.models.ProductModel;
import com.luizalabs.wishlist.domain.ports.CustomerPort;

@WebMvcTest(RemoveProductFromWishlistUseCase.class)
public class RemoveProductFromWishlistUseCaseTests {

  @MockBean
  private CustomerPort customerPort;

  private RemoveProductFromWishlistUseCase removeProductFromWishlistUseCase;

  @BeforeEach
  void setUp() {
    removeProductFromWishlistUseCase = new RemoveProductFromWishlistUseCase(customerPort);
  }

  @Test
  void shouldRemoveProductFromWishlistSuccessfully() {
    String customerId = "customer1";
    String productId = "product1";

    Product product = new ProductModel(productId);
    Set<Product> wishlist = new HashSet<>();
    wishlist.add(product);
    CustomerModel customer = new CustomerModel(customerId, wishlist);

    when(customerPort.findById(customerId)).thenReturn(Optional.of(customer));
    when(customerPort.existsByCustomerIdAndProductId(customerId, productId)).thenReturn(true);

    ResponseEntity<String> response = removeProductFromWishlistUseCase.execute(customerId, productId);

    // Verifica se o produto foi removido da lista de desejos
    assertFalse(customer.getWishlist().contains(product));
    // Verifica se o cliente foi salvo com a lista de desejos atualizada
    verify(customerPort).save(customer);
    // Verifica o status da resposta e a mensagem
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Produto removido da lista de desejos", response.getBody());
  }

  @Test
  void shouldReturnErrorWhenCustomerNotFound() {
    String customerId = "customer1";
    String productId = "product1";

    when(customerPort.findById(customerId)).thenReturn(Optional.empty());

    ResponseEntity<String> response = removeProductFromWishlistUseCase.execute(customerId, productId);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Cliente não encontrado", response.getBody());
  }

  @Test
  void shouldReturnErrorWhenProductNotInWishlist() {
    String customerId = "customer1";
    String productId = "product1";

    CustomerModel customer = new CustomerModel(customerId, new HashSet<>());

    when(customerPort.findById(customerId)).thenReturn(Optional.of(customer));
    when(customerPort.existsByCustomerIdAndProductId(customerId, productId)).thenReturn(false);

    ResponseEntity<String> response = removeProductFromWishlistUseCase.execute(customerId, productId);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Produto não está na lista de desejos", response.getBody());
  }

}
