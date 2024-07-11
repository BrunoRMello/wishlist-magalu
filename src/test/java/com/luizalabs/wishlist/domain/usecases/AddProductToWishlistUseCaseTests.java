package com.luizalabs.wishlist.domain.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.luizalabs.wishlist.adapters.repositories.CustomerRepository;
import com.luizalabs.wishlist.domain.models.CustomerModel;
import com.luizalabs.wishlist.domain.models.ProductModel;

import com.luizalabs.wishlist.domain.ports.CustomerPort;
import com.luizalabs.wishlist.exceptions.CustomerNotFoundException;
import com.luizalabs.wishlist.exceptions.ProductAlreadyInWishlistException;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class AddProductToWishlistUseCaseTests {

  @Autowired
  private AddProductToWishlistUseCase addProductToWishlistUseCase;

  @Autowired
  private CustomerRepository customerRepository;

  @MockBean
  private CustomerPort customerPort;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldThrowCustomerNotFoundExceptionWhenCustomerNotFound() {

    String customerId = "customer1";
    ProductModel productModel = new ProductModel("product1");

    when(customerPort.findById(customerId)).thenReturn(Optional.empty());

    CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class,
        () -> addProductToWishlistUseCase.execute(customerId, productModel));

    assertEquals("Cliente não encontrado", exception.getMessage());
  }

  @Test
  void shouldThrowProductAlreadyInWishlistExceptionWhenProductAlreadyInWishlist() {
    // Dados de entrada para o teste
    String customerId = "customer1";
    ProductModel productModel = new ProductModel("product1");
    ProductModel existingProduct = new ProductModel(productModel.getId());
    CustomerModel customer = new CustomerModel(customerId,
        Set.of(existingProduct));

    when(customerPort.findById(customerId)).thenReturn(Optional.of(customer));

    ProductAlreadyInWishlistException exception = assertThrows(ProductAlreadyInWishlistException.class,
        () -> addProductToWishlistUseCase.execute(customerId, productModel));

    assertEquals("Produto já está na lista de desejos.", exception.getMessage());
  }

  @Test
  void shouldAddProductToWishlistWhenProductIsNotInWishlist() {
    // Configuração do teste de integração usando o banco MongoDB embutido
    String customerId = "customer1";
    ProductModel productModel = new ProductModel("product1");
    CustomerModel customer = new CustomerModel(customerId, new HashSet<>());

    // Simula o comportamento do customerPort
    when(customerPort.findById(customerId)).thenReturn(Optional.of(customer));

    // Adiciona um produto à wishlist
    addProductToWishlistUseCase.execute(customerId, productModel);

    // Salva o cliente atualizado no repositório para validação
    customerRepository.save(customer);

    CustomerModel updatedCustomer = customerRepository.findById(customerId).orElseThrow();
    assertTrue(updatedCustomer.getWishlist().stream().anyMatch(p -> p.getId().equals(productModel.getId())));
  }
}