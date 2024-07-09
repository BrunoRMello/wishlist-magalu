package com.luizalabs.wishlist.domain.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public class CustomerModel {
  private String id;
  private Set<Product> wishlist;
  private Set<ProductModel> products;

  public CustomerModel(String id, Set<Product> wishlist) {
    this.id = id;
    this.wishlist = wishlist == null ? new HashSet<>() : wishlist;
    this.products = new HashSet<>();
  }

  // Construtor sem argumentos
  public CustomerModel() {
    this.wishlist = new HashSet<>();
    this.products = new HashSet<>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Set<Product> getWishlist() {
    return wishlist;
  }

  public void setWishlist(Set<Product> wishlist) {
    this.wishlist = wishlist;
  }

  public Set<ProductModel> getProducts() {
    return products;
  }

  public void setProducts(Set<ProductModel> products) {
    this.products = products;
  }

  public void addProduct(Product product) {
    wishlist.add(product);
  }

  public void removeProduct(String productId) {
    wishlist.removeIf(p -> p.getId().equals(productId));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    CustomerModel that = (CustomerModel) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
