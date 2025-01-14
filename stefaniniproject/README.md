# Task Management API

Esta é uma API de gerenciamento de tarefas construída com Spring Boot, JDBC API e SQL Server, sem a utilização de JPA. A API permite realizar operações CRUD (Create, Read, Update, Delete) em tarefas.

## Requisitos

- Java 11 ou superior
- Maven
- SQL Server
- Postman ou similar para testar os endpoints da API
- JDBC API

## Configuração

### Passo 1: Clonar o Repositório

```bash
git clone https://github.com/seu-usuario/task-management-api.git
cd task-management-api
```
### Passo 2: Configurar o Banco de Dados

Crie um banco de dados no SQL Server e configure as credenciais de acesso no arquivo ```application.properties```:

```
spring.datasource.url=jdbc:sqlserver://localhost:1433;database=<seu_database>
spring.datasource.username=<seu_usuario>
spring.datasource.password=<sua_senha>
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
```
### Passo 3: Executar a Aplicação

Use o Maven para compilar e executar a aplicação:
```bash
mvn clean install
mvn spring-boot:run 
```

## Estrutura do Projeto
```
src/
├── main/
│   ├── java/
│   │   └── com/stefanini/todoList/
│   │       ├── controller/
│   │       │   └── TaskController.java
│   │       ├── model/
│   │       │   ├── Task.java
│   │       │   └── Status.java
│   │       ├── repository/
│   │       │   ├── TaskRepository.java
│   │       │   └── TaskRepositoryImpl.java
│   │       └── service/
│   │           └── TaskService.java
│   └── resources/
│       └── application.properties
└── test/
└── java/
└── com/stefanini/todoList/
└── TaskControllerTest.java
```

# Endpoints da API
## Criar uma Nova Tarefa
```http
POST /api/task
```
### Corpo da Requisição:
```json
{
    "title": "Nova Tarefa",
    "description": "Descrição da nova tarefa",
    "creationDate": "2023-01-01",
    "status": "Pendente"
}
```
## Obter Todas as Tarefas
```http
GET /api/task
```
## Obter uma Tarefa pelo ID
```http
GET /api/task/{id}
```

## Atualizar uma Tarefa
```http
PUT /api/task/{id}
```
### Corpo da Requisição:

```json
{
    "title": "Tarefa Atualizada",
    "description": "Descrição atualizada da tarefa",
    "creationDate": "2023-01-01",
    "status": "Em andamento"
}
```
## Deletar uma Tarefa
```http
DELETE /api/task/{id}
```
