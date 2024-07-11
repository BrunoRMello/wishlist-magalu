package com.luizalabs.wishlist.adapters.controllers;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.luizalabs.wishlist.domain.models.ErrorResponse;
import com.luizalabs.wishlist.domain.models.Product;
import com.luizalabs.wishlist.domain.models.ProductModel;
import com.luizalabs.wishlist.domain.models.SuccessResponse;
import com.luizalabs.wishlist.domain.usecases.AddProductToWishlistUseCase;
import com.luizalabs.wishlist.domain.usecases.GetAllProductsFromWishlistUseCase;
import com.luizalabs.wishlist.domain.usecases.GetOneProductFromWishlistUseCase;
import com.luizalabs.wishlist.domain.usecases.RemoveProductFromWishlistUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
  private final AddProductToWishlistUseCase addProductToWishlistUseCase;
  private final RemoveProductFromWishlistUseCase removeProductFromWishlistUseCase;
  private final GetOneProductFromWishlistUseCase getOneProductFromWishlistUseCase;
  private final GetAllProductsFromWishlistUseCase getAllProductsFromWishlistUseCase;

  public WishlistController(AddProductToWishlistUseCase addProductToWishlistUseCase,
      RemoveProductFromWishlistUseCase removeProductFromWishlistUseCase,
      GetOneProductFromWishlistUseCase getOneProductFromWishlistUseCase,
      GetAllProductsFromWishlistUseCase getAllProductsFromWishlistUseCase) {
    this.addProductToWishlistUseCase = addProductToWishlistUseCase;
    this.removeProductFromWishlistUseCase = removeProductFromWishlistUseCase;
    this.getOneProductFromWishlistUseCase = getOneProductFromWishlistUseCase;
    this.getAllProductsFromWishlistUseCase = getAllProductsFromWishlistUseCase;

  }

  @PostMapping("/{customerId}/products")
  @Tag(name = "Customers", description = "")
  @Operation(summary = "adicionar produtos para lista")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Produto adicionado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class), examples = @ExampleObject(value = "{\"message\": \"Produto adicionado com sucesso\"}"))),
      @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(value = "{\"error\": \"Produto já está na lista\"}"))),
      @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(value = "{\"error\": \"Cliente não encontrado\"}")))

  })

  public ResponseEntity<ProductModel> addProductToWishlist(@PathVariable String customerId,
      @RequestBody ProductModel productModel) {
    ProductModel addedProduct = addProductToWishlistUseCase.execute(customerId, productModel);
    return ResponseEntity.ok(addedProduct);
  }

  @DeleteMapping("/{customerId}/products/{productId}")
  @Tag(name = "Customers")
  @Operation(summary = "remover item da lista")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Produto removido da lista de desejos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class), examples = @ExampleObject(value = "{\"message\": \"Produto removido da lista de desejos\"}"))),
      @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(value = "{\"error\": \"Produto não está na lista de desejos\"}"))),
      @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(value = "{\"error\": \"Cliente não encontrado\"}")))

  })
  public ResponseEntity<String> removeProductFromWishlist(@PathVariable String customerId,
      @PathVariable String productId) {
    ResponseEntity<String> response = removeProductFromWishlistUseCase.execute(customerId, productId);
    return response;
  }

  @GetMapping("/{customerId}/products/{productId}")
  @Tag(name = "Customers")
  @Operation(summary = "Saber se o produto esta na lista")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Produto está na lista de desejos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class), examples = @ExampleObject(value = "{\"message\": \"Produto está na lista de desejos\"}"))),
      @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(value = "{\"error\": \"Produto não está na lista de desejos\"}"))),
      @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(value = "{\"error\": \"Cliente não encontrado\"}")))

  })
  public ResponseEntity<String> getOneProductFromWishlist(@PathVariable String customerId,
      @PathVariable String productId) {
    ResponseEntity<String> response = getOneProductFromWishlistUseCase.execute(customerId, productId);
    return response;
  }

  @GetMapping("/{customerId}/products")
  @Tag(name = "Customers")
  @Operation(summary = "Obter todos os produtos da lista de desejos", description = "Retorna uma lista de produtos que estão na lista de desejos do cliente especificado.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Produtos da lista de desejos recuperados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class, type = "array", example = "[{\"id\": \"product2\"}, {\"id\": \"product\"}]"), examples = @ExampleObject(value = "[{\"id\": \"product2\"}, {\"id\": \"product\"}]"))),
      @ApiResponse(responseCode = "400", description = "Erro de requisição inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(value = "{\"error\": \"Cliente não encontrado\"}"))),
      @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(value = "{\"error\": \"Cliente não encontrado\"}")))
  })
  public ResponseEntity<Set<Product>> getAllProductsFromWishlist(@PathVariable String customerId) {
    Set<Product> products = getAllProductsFromWishlistUseCase.execute(customerId);
    return ResponseEntity.ok(products);
  }
}
