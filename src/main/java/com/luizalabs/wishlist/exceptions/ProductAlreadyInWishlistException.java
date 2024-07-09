package com.luizalabs.wishlist.exceptions;

public class ProductAlreadyInWishlistException extends RuntimeException {
  public ProductAlreadyInWishlistException(String message) {
    super(message);
  }
}