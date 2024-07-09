package com.luizalabs.wishlist.domain.usecases;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.luizalabs.wishlist.domain.models.CustomerModel;
import com.luizalabs.wishlist.domain.ports.CustomerPort;

@Component

public class RemoveProductFromWishlistUseCase {
  private final CustomerPort customerPort;

  public RemoveProductFromWishlistUseCase(CustomerPort customerPort) {
    this.customerPort = customerPort;
  }

  public ResponseEntity<String> execute(String customerId, String productId) {
    Optional<CustomerModel> customerOpt = customerPort.findById(customerId);

    if (customerOpt.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }

    CustomerModel customer = customerOpt.get();

    boolean productExistsInWishlist = customerPort.existsByCustomerIdAndProductId(customerId, productId);

    if (!productExistsInWishlist) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto não está na lista de desejos");
    }

    customer.removeProduct(productId);
    customerPort.save(customer);

    return ResponseEntity.ok("Produto removido da lista de desejos");
  }
}