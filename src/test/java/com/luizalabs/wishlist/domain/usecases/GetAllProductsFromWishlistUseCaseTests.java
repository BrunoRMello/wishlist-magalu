package com.luizalabs.wishlist.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

//import org.h2.command.dml.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.luizalabs.wishlist.adapters.repositories.CustomerRepository;
import com.luizalabs.wishlist.domain.models.CustomerModel;
import com.luizalabs.wishlist.domain.models.Product;
import com.luizalabs.wishlist.domain.models.ProductModel;
import com.luizalabs.wishlist.domain.ports.CustomerPort;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class GetAllProductsFromWishlistUseCaseTests {
  private static final Logger logger = LoggerFactory.getLogger(GetAllProductsFromWishlistUseCaseTests.class);

  @Autowired
  private GetAllProductsFromWishlistUseCase getAllProductsFromWishlistUseCase;

  @SuppressWarnings("unused")
  @Autowired
  private CustomerRepository customerRepository;

  @MockBean
  private CustomerPort customerPort;

  @BeforeEach
  void setUp() {

  }

  @Test
  void shouldReturnAllProductsFromWishlist() {
    String customerId = "customer1";

    Product product1 = new ProductModel("product1");
    Product product2 = new ProductModel("product2");
    Product product3 = new ProductModel("product3");

    Set<Product> wishlist = new HashSet<>();
    wishlist.add(product1);
    wishlist.add(product2);
    wishlist.add(product3);

    CustomerModel customer = new CustomerModel(customerId, wishlist);

    // Simula o comportamento do customerPort
    when(customerPort.findById(customerId)).thenReturn(Optional.of(customer));

    // Executa o caso de uso
    Set<Product> products = getAllProductsFromWishlistUseCase.execute(customerId);

    assertEquals(3, products.size());
    assertTrue(products.contains(product1));
    assertTrue(products.contains(product2));
    assertTrue(products.contains(product3));

    logger.info("Produtos retornados: {}", products);

  }

  @Test
  void shouldThrowExceptionWhenCustomerNotFound() {
    // Configuração do teste de integração usando o banco MongoDB embutido
    String customerId = "nonexistent_customer";

    // Simula o comportamento do customerPort para um cliente inexistente
    when(customerPort.findById(customerId)).thenReturn(Optional.empty());

    // Executa o caso de uso e verifica se a exceção é lançada
    assertThrows(RuntimeException.class, () -> getAllProductsFromWishlistUseCase.execute(customerId));
  }

}
