package com.luizalabs.wishlist.adapters.repositories;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.luizalabs.wishlist.domain.models.CustomerModel;
import com.luizalabs.wishlist.domain.ports.CustomerPort;

import java.util.Optional;

@Repository
public class CustomerRepositoryAdapter implements CustomerPort {
  private final CustomerRepository customerRepository;
  private final MongoTemplate mongoTemplate;

  public CustomerRepositoryAdapter(CustomerRepository customerRepository, MongoTemplate mongoTemplate) {
    this.customerRepository = customerRepository;
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public CustomerModel save(CustomerModel customerModel) {
    return customerRepository.save(customerModel);
  }

  @Override
  public Optional<CustomerModel> findById(String customerId) {
    return customerRepository.findById(customerId);
  }

  @Override
  public Optional<CustomerModel> findByIdAndProductId(String customerId, String productId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(customerId).and("wishlist._id").is(productId));
    CustomerModel customerModel = mongoTemplate.findOne(query, CustomerModel.class);
    return Optional.ofNullable(customerModel);
  }

  @Override
  public boolean existsById(String customerId) {
    return customerRepository.existsById(customerId);
  }

  @Override
  public boolean existsByCustomerIdAndProductId(String customerId, String productId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(customerId).and("wishlist._id").is(productId));
    return mongoTemplate.exists(query, CustomerModel.class);
  }

  @Override
  public void removeProductFromWishlist(String customerId, String productId) {
    Optional<CustomerModel> customerOpt = findById(customerId);
    if (customerOpt.isPresent()) {
      CustomerModel customer = customerOpt.get();
      customer.removeProduct(productId);
      save(customer);
    }
  }
}
