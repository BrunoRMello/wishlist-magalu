package com.luizalabs.wishlist.domain.ports;

import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;

import com.luizalabs.wishlist.domain.models.CustomerModel;

public interface CustomerPort {

  CustomerModel save(CustomerModel customerModel);

  Optional<CustomerModel> findById(String customerId);

  @Query(value = "{ '_id': ?0, 'wishlist._id': ?1 }")
  Optional<CustomerModel> findByIdAndProductId(String customerId, String productId);

  boolean existsById(String customerId);

  @Query(value = "{ '_id': ?0, 'wishlist._id': ?1 }", exists = true)
  boolean existsByCustomerIdAndProductId(String customerId, String productId);

  void removeProductFromWishlist(String customerId, String productId);

  // Object findByIdAndProductId(String customerId, String productId);

}
