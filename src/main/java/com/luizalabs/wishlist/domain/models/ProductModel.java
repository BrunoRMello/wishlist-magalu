package com.luizalabs.wishlist.domain.models;

import java.util.Objects;

public class ProductModel implements Product {
  private String id;

  // Adicionar um construtor padrão para @RequestBody
  public ProductModel() {
  }

  public ProductModel(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ProductModel that = (ProductModel) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  // Para retornar nos testes *Saber se o teste esta com retorno do useCase
  @Override
  public String toString() {
    return "ProductModel{" +
        "id='" + id + '\'' +
        '}';
  }
}
