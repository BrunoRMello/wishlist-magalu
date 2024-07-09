package com.luizalabs.wishlist.domain.usecases;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.luizalabs.wishlist.domain.ports.CustomerPort;

@Component
public class GetOneProductFromWishlistUseCase {
  private final CustomerPort customerPort;

  public GetOneProductFromWishlistUseCase(CustomerPort customerPort) {
    this.customerPort = customerPort;
  }

  public ResponseEntity<String> execute(String customerId, String productId) {
    boolean productExistsInWishlist = customerPort.existsByCustomerIdAndProductId(customerId, productId);

    if (productExistsInWishlist) {
      return ResponseEntity.ok("Produto está na lista de desejos");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não está na lista de desejos");
    }
  }

}
