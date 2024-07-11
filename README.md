<div align="left">
  <img src="https://img.shields.io/static/v1?label=Progress&message=100%&color=009CA3&style=plastic&logo=spring" alt="Progress"/>
</div>

<br/>

<div align="center">
  <img src="https://github.com/BrunoRMello/wishlist-magalu/assets/59987398/741095d3-4201-4da4-a98a-28edeff892fd" alt="logo-luiza-labs" width="400px"/>
</div>
<br/>

<div align="center">
  <h1>Wishlist Application</h1>
  <h3>Uma API desenvolvida com <a href="https://spring.io/projects/spring-boot">Spring Boot</a> e <a href="https://www.mongodb.com/">MongoDB</a>, que permite gerenciar uma lista de desejos de clientes.</h3>
</div>

<div align="center">
  <img src="https://img.shields.io/static/v1?label=Java&message=20&color=009CA3&style=plastic&logo=java" alt="Versão do Java" />
  <img src="https://img.shields.io/static/v1?label=Spring%20Boot&message=3.3.1&color=009CA3&style=plastic&logo=spring" alt="Versão do Spring Boot" />
  <img src="https://img.shields.io/static/v1?label=MongoDB&message=latest&color=009CA3&style=plastic&logo=mongodb" alt="Versão do MongoDB" />
</div>

<br/>

<div align="center">
  <a href="#tecnologias">Tecnologias</a> •
  <a href="#como-usar">Como usar</a> •
  <a href="#documentacao">Documentação</a> •
  <a href="#decisoes-tecnicas">Decisões Técnicas</a> •
  <a href="#desafios">Desafios</a> •
  <a href="#melhorias">Melhorias</a> •
  <a href="#contato">Contato</a>
</div>

<br/>

<h2 id="tecnologias">Tecnologias:</h2>
<h3>Linguagens e Ferramentas:</h3>
<ul>
  <li><strong>Java:</strong> Linguagem de programação utilizada para desenvolver a aplicação.</li>
  <li><strong>Spring Boot:</strong> Framework que facilita a criação de aplicações standalone e de produção com o Spring.</li>
  <li><strong>MongoDB:</strong> Banco de dados NoSQL utilizado para armazenar os dados da aplicação.</li>
</ul>

<h3>Principais Bibliotecas:</h3>
<ul>
  <li><strong>Lombok:</strong> Biblioteca que simplifica a escrita de código Java.</li>
  <li><strong>Spring Data MongoDB:</strong> Biblioteca do Spring para facilitar a integração com o MongoDB.</li>
  <li><strong>Springdoc OpenAPI:</strong> Biblioteca para gerar documentação interativa da API com Swagger.</li>
  <li><strong>JUnit:</strong> Framework de testes para Java.</li>
  <li><strong>Mockito:</strong> Framework de mock para testes unitários em Java.</li>
</ul>

<h3>Outras Ferramentas:</h3>
<ul>
  <li><strong>Docker:</strong> Utilizado para criar contêineres para a aplicação e o banco de dados.</li>
  <li><strong>Docker Compose:</strong> Utilizado para orquestrar os contêineres Docker.</li>
</ul>

<h2 id="como-usar">Como usar:</h2>
<h3>Requisitos:</h3>
<p>
  Antes de iniciar, certifique-se de ter instalado em sua máquina as seguintes ferramentas:
</p>

<ul>
  <li>Git</li>
  <li>Java 20</li>
  <li>Maven</li>
  <li>Docker</li>
  <li>Docker Compose</li>
</ul>

<h3>Clone o projeto e acesse a pasta:</h3>

```bash
$ git clone git@github.com:BrunoRMello/wishlist-magalu.git
```

<h3>Siga os passos abaixo para executar a aplicação localmente:</h3>

# Compile o projeto

```bash
$ mvn clean package -DskipTests
```
# Execute a aplicação
```$ java -jar target/wishlist-0.0.1-SNAPSHOT.jar```

<h3>Para executar os testes:</h3>

```bash
 $ mvn test
 ```

# <h3>Rodando com Docker:</h3>
<p>Se preferir, você pode executar a aplicação usando Docker. Siga os passos abaixo:</p>


``docker-compose up`` or ``docker-compose up -d``

<h3>Rodando localmente sem docker</h3>

Descomentar o arquivo application.properties a linha
```#spring.data.mongodb.uri=mongodb://localhost:27017/wishlist```




<p>Este comando irá construir a imagem do Docker e iniciar a aplicação dentro de um container.</p>

<h3 id='documentacao'>Documentação:</h3>
<p>Acesse a documentação completa da API em <code>http://localhost:8080/swagger-ui.html</code></p>

<h2 id="decisoes-tecnicas">Decisões Técnicas:</h2>
<h3>Spring Boot:</h3>
<p>O Spring Boot foi escolhido pela sua capacidade de simplificar a configuração e o desenvolvimento de aplicações Java standalone e de produção.</p>
<h3>MongoDB:</h3>

<p>MongoDB foi escolhido como banco de dados NoSQL pela sua flexibilidade e facilidade de uso com o Spring Data.</p>

<h3>JUnit e Mockito:</h3>

<p>JUnit e Mockito são frameworks de testes amplamente utilizados que permitem criar testes unitários e de integração de forma simples e eficiente.</p>

<div>
  <h2 id="desafios">Desafios:</h2>
  <p>Este projeto é o meu primeiro desenvolvimento com <strong>Spring Boot</strong>, e durante o processo, enfrentei vários desafios significativos. Em particular, a implementação de testes foi um aspecto desafiador, pois eu não tinha conhecimento prévio sobre testes em Java e Spring Boot.</p>
  <p>No início, eu estava apenas começando a aprender sobre a plataforma, então foi necessário me dedicar a assistir aulas e buscar materiais sobre como escrever testes unitários e de integração. Aprendi a utilizar frameworks como <strong>JUnit</strong> e <strong>Mockito</strong>, e apliquei esses conhecimentos para desenvolver testes básicos para a aplicação.</p>
  <p>Apesar do esforço para entender e implementar esses conceitos, reconheço que há espaço para melhorias e que uma cobertura de testes mais abrangente poderia ser benéfica. Este projeto foi uma excelente oportunidade para expandir meus conhecimentos em <strong>testes</strong> e estou ansioso para continuar aprendendo e aprimorando minhas habilidades na área.</p>
</div>

<h2 id="melhorias">Melhorias:</h2>
<p>Embora a aplicação já esteja funcional, ainda há espaço para melhorias, como adicionar análise de uso e relatórios detalhados sobre os dados armazenados.</p>

<h2 id="contato">Contato:</h2>
<h3>Estou à disposição para esclarecer dúvidas, receber sugestões ou lidar com críticas. Não hesite em entrar em contato!</h3>

<p>Email: mello.bruno@live.com</p>

<p>LinkedIn: <a href="https://www.linkedin.com/in/bruno-mello-4a4846123/">https://www.linkedin.com/in/bruno-mello-4a4846123</a></p>

<h3>Obrigado pela oportunidade!</h3>

<h2>Resultados dos Testes:</h2>

<p>Veja abaixo os resultados dos testes realizados:</p>

![testes_spring](https://github.com/BrunoRMello/wishlist-magalu/assets/59987398/21775bb7-0e0b-453f-8ff2-ba9e2ed96421)


<h3>Estrutua do projeto</h3>

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