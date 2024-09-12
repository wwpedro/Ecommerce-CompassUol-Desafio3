# E-commerce API - Spring Boot Project

Este projeto é uma API RESTful desenvolvida em **Spring Boot**, utilizando o **Maven** como ferramenta de gerenciamento de dependências. A API simula um sistema de e-commerce, permitindo o gerenciamento de usuários, produtos, vendas, e estoques.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.3**
- **Spring Data JPA**
- **Spring Security com JWT**
- **Flyway para migração de banco de dados**
- **MySQL**
- **Maven**
- **Lombok**
- **Insomnia ou Postman para testes de API**

## Como Rodar o Projeto

### Pré-requisitos

- Java 17+
- Maven
- MySQL
- Flyway (configurado no projeto para migração do banco de dados)

### Configuração do Banco de Dados

Certifique-se de que seu banco de dados MySQL está configurado corretamente. O projeto utiliza as seguintes configurações de banco de dados, definidas no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost/ecommerce1
spring.datasource.username=root
spring.datasource.password=root123
spring.flyway.baseline-on-migrate=true
```

### Comandos para Criar as Tabelas no Banco de Dados

Utilize o Flyway para gerar as tabelas automaticamente no banco de dados. Basta rodar o projeto que ele executará as migrações.

Caso queira criar as tabelas manualmente, aqui está o script SQL básico para a criação das tabelas principais:

```sql
CREATE TABLE Produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    descricao TEXT,
    preco DECIMAL(10, 2),
    ativo BOOLEAN
);

CREATE TABLE Estoque (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    produto_id BIGINT,
    quantidade_atual INT,
    FOREIGN KEY (produto_id) REFERENCES Produto(id)
);

CREATE TABLE Usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255),
    senha VARCHAR(255),
    permissao VARCHAR(50)
);

CREATE TABLE Venda (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data DATETIME,
    total DECIMAL(10, 2),
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);

CREATE TABLE ItemVenda (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    produto_id BIGINT,
    venda_id BIGINT,
    quantidade INT,
    preco_unitario DECIMAL(10, 2),
    FOREIGN KEY (produto_id) REFERENCES Produto(id),
    FOREIGN KEY (venda_id) REFERENCES Venda(id)
);
```

### Rodando o Projeto

1. Clone o repositório:

```bash
git clone <url-do-repositorio>
```

2. Navegue até a pasta do projeto:

```bash
cd comercio-desafio-tres
```

3. Compile e rode o projeto com o Maven:

```bash
mvn spring-boot:run
```

4. Acesse a aplicação em:

```
http://localhost:8080
```

### Modelos de ER

O diagrama de Entidade-Relacionamento (ER) reflete as seguintes entidades e seus relacionamentos:

- **Produto** possui um **Estoque**.
- **Usuario** realiza **Venda(s)**.
- **Venda** contém vários **ItensVenda**, que são relacionados a **Produto(s)**.

### Usando a API com Insomnia

Aqui estão alguns exemplos de como você pode testar a API usando o **Insomnia**.

#### 1. Autenticação (Login)

**POST** `/api/auth/login`

Corpo da requisição:

```json
{
  "email": "usuario@example.com",
  "senha": "senha123"
}
```

Resposta:

```json
{
  "token": "JWT_TOKEN",
  "isAdmin": true
}
```

#### 2. Listar Produtos

**GET** `/api/produtos`

Cabeçalhos:

- `Authorization`: `Bearer JWT_TOKEN`

Resposta:

```json
[
  {
    "id": 1,
    "nome": "Produto A",
    "descricao": "Descrição do Produto A",
    "preco": 100.0,
    "ativo": true
  }
]
```

#### 3. Criar Produto (Apenas Admin)

**POST** `/api/produtos`

Corpo da requisição:

```json
{
  "nome": "Produto B",
  "descricao": "Descrição do Produto B",
  "preco": 50.0
}
```

Resposta:

```json
{
  "id": 2,
  "nome": "Produto B",
  "descricao": "Descrição do Produto B",
  "preco": 50.0,
  "ativo": true
}
```

#### 4. Listar Usuários (Apenas Admin)

**GET** `/api/usuarios`

Cabeçalhos:

- `Authorization`: `Bearer JWT_TOKEN`

Resposta:

```json
[
  {
    "id": 1,
    "nome": "Usuário Admin",
    "email": "admin@example.com",
    "permissao": "ADMIN"
  }
]
```
