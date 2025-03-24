# Desafio Prático: API de Cálculo de Impostos

## Descrição:
Este projeto é uma API RESTful desenvolvida para gerenciar e calcular impostos no Brasil. A API permite o registro de diferentes tipos de impostos (ICMS, ISS, IPI, entre outros) e realiza cálculos com base no tipo de imposto e no valor base fornecido. Além disso, a API é segura, utilizando Spring Security e JWT (JSON Web Token) para autenticação e autorização.

## Pré-requisitos

Antes de começar, certifique-se de ter os seguintes itens instalados em sua máquina:

- [Java 17+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- Uma IDE como [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou [Eclipse](https://www.eclipse.org/ide/).
- [Postgres](https://www.postgresql.org/download/)

## Funcionalidades:
- Gerenciamento de Tipos de Impostos:
    - Lista todos os tipos de impostos disponíveis.
    - Cadastra novos tipos de impostos (nome, descrição e alíquota).
    - Obtem detalhes de um tipo de imposto específico pelo ID.
    - Exclui um tipo de imposto pelo ID.
  

- Cálculo de Impostos:
    - Calcula o valor do imposto com base no tipo de imposto e no valor base fornecido.


- Segurança:
  - Implementação de autenticação e autorização utilizando Spring Security e JWT.
  Apenas usuários autenticados podem acessar os endpoints.
  O acesso a endpoints de criação, exclusão e cálculo de impostos é restrito para usuários com o papel de ADMIN.

## Estrutura do Código:
* **CalculoimpostoApplication():** Classe responsável por rodar a aplicação e entrar no banco de dados.
* **CalculoController:** Classe que controla a Rota do EndPoint referentes ao calculo de impostos(Calcular imposto).
* **TipoImpostoController:** Classe que controla as Rotas dos EndPoints referentes a Impostos(Exibir detalhes do imposto por ID, Listar os tipos de impostos, Cadastrar imposto,  Excluir imposto).
* **UserController():** Classe que controla as Rotas dos EndPoints referentes ao Usuário(Login e Cadastar usuário).
* **AuthResponseDto():** Classe que cuida da transferência dos dados do Token.
* **CalculoDto():** Classe que cuida da transferência dos dados da realização dos cálculos.
* **CalculoResponseDto():** Classe que cuida da transferência dos dados da resposta dos cálculos.
* **ErrorDTO():** Classe que cuida da transferência dos dados dos erros.
* **LoginDto():** Classe que cuida da transferência dos dados para login.
* **RegisterUserDto():** Classe que cuida da transferência dos dados para registrar o usuário.
* **RegisterUserResponseDto():** Classe que cuida da transferência dos dados para a resposta do registro de usuário.
* **TipoImpostoDto():** Classe que cuida da transferência dos dados para listar os impostos.
* **CustomAccessDeniedHandler** Classe que implementa a excessão AccessDeniedHandler.
* **GlobalExceptionHandler** Classe que contém métodos para personalizações de exceções (UniqueConstraintViolationException.class, HttpMessageNotReadableException.class, ResourceNotFoundException.class, IllegalArgumentException.class, HttpStatus.UNAUTHORIZED, JwtException.class e Exception.class).
* **ResourceNotFoundException** Classe que extende a excessão RuntimeException.
* **UniqueConstraintViolationException** Classe que extende a excessão RuntimeException.
* **JwtAuthenticationEntryPoint** Classe que implementa AuthenticationEntryPoint para configuração do JWT
* **JwtAuthenticationFilter** Classe que implementa OncePerRequestFilter para configuração do JWT
* **JwtTokenProvider** Classe que gera um token JWT
* **SecurityConfig** Classe que configura o Spring Security
* **SwaggerConfig** Classe que configura o Swagger
* **Role():** Classe que determina entidade Role.
* **Roles():** Enum que determina o que será cadastrado na entidade Role.
* **TipoImposto():** Classe que determina entidade Tipo_Produto.
* **User():** Classe que determina entidade User.
* **RoleRepository():** Interface que extends o JPA para Role.
* **TipoImpostoRepository():** Interface que extends o JPA para TipoImposto.
* **UserRepository():** Interface que extends o JPA para User.
* **AuthService():** Classe que contem o método referente a Login(login).
* **CalculoService():** Classe que contem o método referente a Cálculo(calculoImposto).
* **CustomUserDatailsService():** Classe que contem o método referente a Autorizações do JWT(loadUserByUsername).
* **TipoImpostoService():** Classe que contem os métodos referentes ao TipoImposto(listarimpostos, cadastraImposto, listaImpostoPorID, impostoExiste e excluirImposto).
* **UserService():** Classe que contem o método referente ao User(registerUser).


## Como Executar o Código

Para executar o código, siga os passos abaixo:

### 1. Clonar o Repositório
Clone o repositório para o seu ambiente local:

```bash
git clone git@github.com:ericamoraesabdao/calculoimposto.git
```

### 2. Navegar até o Diretório do Projeto
Entre na pasta do repositório clonado:

```bash
cd calculoimposto
```

### 3. Configure as variáveis de ambiente para o banco de dados:
    export DB_HOST=localhost
    export DB_NAME=calculoimposto
    export DB_PASSWORD=postgres
    export DB_PORT=5433
    export DB_USERNAME=postgres

### 4. Compile e execute o projeto:
    mvn clean install
    mvn spring-boot:run

### 5. Acesse a documentação Swagger para testar os endpoints:
URL: http://localhost:8080/swagger-ui.html

## Documentação Swagger
A aplicação possui uma documentação interativa das APIs gerada pelo Swagger Automagicamente.

Certifique-se de que a aplicação está rodando. 
Abra o navegador e acesse:

http://localhost:8080/swagger-ui.html

Na interface do Swagger, você pode:

Visualizar todas as rotas disponíveis.
Testar as APIs diretamente na interface.

### Processo de Autenticação no Swagger 🔒
A API utiliza o Swagger para documentar e testar os endpoints. Como a API é protegida por autenticação JWT, é necessário fornecer um token válido para acessar os endpoints protegidos diretamente pelo Swagger UI.

### Como funciona a autenticação no Swagger:
#### Endpoint de Login:

Antes de acessar os endpoints protegidos, você deve autenticar-se utilizando o endpoint de login:

POST /user/login

    {
    "username": "usuario123",
    "password": "senhaSegura"
    }
A resposta será um token JWT:
    
    {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }

#### Configurar o Token no Swagger:

No Swagger UI, clique no botão Authorize no canto superior direito.

Uma janela será exibida solicitando o token de autenticação.

Insira o token.

#### Acesso aos Endpoints:

Após autorizar, você poderá acessar os endpoints protegidos diretamente pelo Swagger UI.

O token será automaticamente incluído no cabeçalho das requisições como Authorization: Bearer <seu_token>.

#### Dica 
Certifique-se de que o token JWT gerado pelo endpoint de login não tenha expirado antes de usá-lo no Swagger.

O tempo de expiração do token é de 1 hora.

#### Benefícios da Autenticação no Swagger

Permite testar endpoints protegidos diretamente na interface do Swagger.
Garante que apenas usuários autenticados possam acessar recursos sensíveis.
Facilita o desenvolvimento e a validação da API.

## Testes
Os testes foram implementados utilizando JUnit e Jacoco.
Para executar os testes, utilize o comando:

    mvn test


## Tecnologias Utilizadas
- **Java 17:** Linguagem de programação.
- **Spring Boot:** Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA:** Para persistência de dados.
- **Postgres:** Banco de dados para desenvolvimento e testes.
- **Maven:** Gerenciador de dependências e automação de build.
- **Validation** Verifica validações como @NotNull
- **Spring Security:** Cuida da autenticação.
- **JWT (JSON Web Token):** Cuida das autorizações
- **Swagger/OpenAPI:** Gerencia os endpoints
- **JUnit:** Auxilia os testes unitários
- **Jacoco:** Faz relatórios de cobertura de testes.
- **Lombok:** Gerencia Get e Setters entre outros.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

## Autora

- **Erica Moraes Abdao**
