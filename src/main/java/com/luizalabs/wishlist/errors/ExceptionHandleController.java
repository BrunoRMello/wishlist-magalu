package com.luizalabs.wishlist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//import com.luizalabs.wishlist.domain.usecases.ProductAlreadyInWishlistException;

@ControllerAdvice
public class ExceptionHandleController {

  // @ExceptionHandler(ProductAlreadyInWishlistException.class)
  // public ResponseEntity<String>
  // handleProductAlreadyInWishlistException(ProductAlreadyInWishlistException e)
  // {
  // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  // }

  // @ExceptionHandler(HttpMessageNotReadableException.class)
  // public ResponseEntity<String>
  // handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
  // return
  // ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());
  // }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
  }

  // Adicione um handler genérico para erros não capturados
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + e.getMessage());
  }
}