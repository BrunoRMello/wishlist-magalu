```bash
│   .gitignore                        
│   HELP.md                          
│   mvnw                              
│   mvnw.cmd                          
│   pom.xml                         
│
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───luizalabs
│   │   │           └───wishlist
│   │   │               │   WishlistApplication.java         -> Classe principal do Spring Boot que inicializa a aplicação.
│   │   │               │
│   │   │               ├───adapters
│   │   │               │   ├───controllers
│   │   │               │   │       WishlistController.java  -> Controlador REST para gerenciar as requisições da API de wishlist.
│   │   │               │   └───repositories
│   │   │               │           CustomerRepository.java  -> Interface do repositório para operações com dados de clientes.
│   │   │               │           CustomerRepositoryAdapter.java -> Adaptador do repositório de clientes.
│   │   │               │
│   │   │               ├───domain
│   │   │               │   ├───models
│   │   │               │   │       Customer.java           -> Classe representando o cliente.
│   │   │               │   │       Product.java            -> Classe representando o produto.
│   │   │               │   │       SuccessResponse.java    -> Classe para resposta de sucesso.
│   │   │               │   │       ErrorResponse.java      -> Classe para resposta de erro.
│   │   │               │   ├───ports
│   │   │               │   │       CustomerPort.java       -> Porta de comunicação para operações com clientes.
│   │   │               │   └───usecases
│   │   │               │           AddProductToWishlistUseCase.java      -> Caso de uso para adicionar um produto à wishlist.
│   │   │               │           GetAllProductsFromWishlistUseCase.java -> Caso de uso para obter todos os produtos da wishlist.
│   │   │               │           GetOneProductFromWishlistUseCase.java  -> Caso de uso para obter um produto específico da wishlist.
│   │   │               │           RemoveProductFromWishlistUseCase.java  -> Caso de uso para remover um produto da wishlist.
│   │   │               │
│   │   │               ├───errors
│   │   │               │       ExceptionHandleController.java -> Controlador para tratar exceções.
│   │   │               └───exceptions
│   │   │                       CustomerNotFoundException.java -> Exceção para quando o cliente não é encontrado.
│   │   │                       ProductAlreadyInWishlistException.java -> Exceção para quando o produto já está na wishlist.
│   │   └───resources
│   │       │   application.properties
│   └───test
│       ├───java
│       │   └───com
│       │       └───luizalabs
│       │           └───wishlist
│       │               ├───usecases
│       │               │       AddProductToWishlistUseCaseTests.java
│       │               │       GetAllProductsFromWishlistUseCaseTests.java
│       │               │       GetOneProductFromWishlistUseCaseTests.java
│       │               │       RemoveProductFromWishlistUseCaseTests.java
│       └───resources
│               application-test.yml
```