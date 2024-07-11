package com.luizalabs.wishlist.domain.usecases;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.luizalabs.wishlist.domain.models.CustomerModel;

import com.luizalabs.wishlist.domain.models.ProductModel;
import com.luizalabs.wishlist.domain.ports.CustomerPort;
import com.luizalabs.wishlist.exceptions.CustomerNotFoundException;
import com.luizalabs.wishlist.exceptions.ProductAlreadyInWishlistException;

@Component
public class AddProductToWishlistUseCase {
  private final CustomerPort customerPort;

  public AddProductToWishlistUseCase(CustomerPort customerPort) {
    this.customerPort = customerPort;
  }

  public ProductModel execute(String customerId, ProductModel productModel) {
    // Find the customer by ID
    Optional<CustomerModel> customerOpt = customerPort.findById(customerId);

    if (customerOpt.isEmpty()) {
      throw new CustomerNotFoundException("Cliente não encontrado");
    }

    CustomerModel customer = customerOpt.get();

    boolean productAlreadyInWishlist = customer.getWishlist().stream()
        .anyMatch(p -> p.getId().equals(productModel.getId()));

    if (productAlreadyInWishlist) {
      throw new ProductAlreadyInWishlistException("Produto já está na lista de desejos.");
    }

    customer.addProduct(productModel);

    customerPort.save(customer);

    return productModel;
  }
}