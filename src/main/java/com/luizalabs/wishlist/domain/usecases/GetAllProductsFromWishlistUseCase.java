package com.luizalabs.wishlist.domain.usecases;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.luizalabs.wishlist.domain.models.CustomerModel;
import com.luizalabs.wishlist.domain.models.Product;

import com.luizalabs.wishlist.domain.ports.CustomerPort;

@Component
public class GetAllProductsFromWishlistUseCase {
  private final CustomerPort customerPort;

  public GetAllProductsFromWishlistUseCase(CustomerPort customerPort) {
    this.customerPort = customerPort;
  }

  public Set<Product> execute(String customerId) {

    // Find the customer by ID
    Optional<CustomerModel> customerOpt = customerPort.findById(customerId);

    if (customerOpt.isEmpty()) {
      throw new RuntimeException("Cliente não encontrado");
    }

    CustomerModel customer = customerOpt.get();

    return customer.getWishlist();
  }
}