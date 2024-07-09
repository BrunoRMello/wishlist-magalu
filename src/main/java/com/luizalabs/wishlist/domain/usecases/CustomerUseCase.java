package com.luizalabs.wishlist.domain.usecases;

import org.springframework.stereotype.Component;

import com.luizalabs.wishlist.adapters.repositories.CustomerRepository;
import com.luizalabs.wishlist.domain.models.CustomerModel;

@Component
public class CustomerUseCase {
  private final CustomerRepository customerRepository;

  public CustomerUseCase(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public CustomerModel save(CustomerModel customerModel) {
    return customerRepository.save(customerModel);
  }

  public CustomerModel findById(String customerId) {
    return (CustomerModel) customerRepository.findById(customerId)
        .orElseThrow();
  }
}
