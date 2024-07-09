package com.luizalabs.wishlist.domain.models;

import java.util.Set;

public interface Customer {
  String getId();

  Set<Product> getWishlist();

  void addProduct(Product product);

  void removeProduct(String productId);

  Customer save(Customer customer);

  Customer findById(String customerId);

  Customer findByIdAndProductId(String customerId, String productId);

  void removeProductFromWishlist(String customerId, String productId);

  Customer orElse(Object object);

  // void removeProduct(String productId);
}
