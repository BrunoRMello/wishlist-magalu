package com.luizalabs.wishlist.adapters.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.luizalabs.wishlist.domain.models.CustomerModel;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerModel, String> {
  Optional<CustomerModel> findById(String id);

  CustomerModel save(CustomerModel customer);

  void deleteById(String id);
}
