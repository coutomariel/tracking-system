# API de Tracking System

Uma API para monitoramento de paradas de veículos em pontos geográficos, construída desenvovlida em kotlin com Java 8 e Spring Boot 2.7.16, utilizando MongoDB como banco de dados.

A escolha pelo Mongo como mongo como base de dados se deu por seu suporte a dados geoespacias e integração com com spring-data-mongo.


## Endpoints

### Adição de pontos de interesse

Adiciona um ponto de interesse
- **Método:** POST
- **Rota:** `/spots`

### Adição de posicionamentos
Adiciona um posicionamento em ponto geográfico de um veículo
- **Método:** POST
- **Rota:** `/positions`

### Consulta da quantidade de paradas de em pontos de interesse por placa

Obtém a lista de pontos de interesse, com as placas e suas quantidades de paradas nesse ponto
- **Método:** GET
- **Rota:** `/positions`

## Documentação da API

A documentação da API está disponível no formato Swagger OpenAPI JSON na raiz do projeto, no arquivo `api-docs.json`.

### swagger-editor
Você pode visualizar e interagir com a documentação usando o [Swagger Editor](https://editor.swagger.io/).
1. Acesse o [Swagger Editor](https://editor.swagger.io/).
2. No Swagger Editor, clique em "File" e selecione "Import File".
3. Carregue o arquivo `api-docs.json` do diretório raiz do projeto.
4. Agora você pode explorar a documentação da API, testar os endpoints e entender como a API funciona.

### swagger-ui
Além disso, com a aplicação rodando em ambiente local é possível visualizar o doc através do http://localhost:8080/swagger-ui/index.html

### postman-collection
Também disponibilizado na pasta raiz da aplicação um arquivo `Mobi7-tracking.postman_collection.json` contendo a collection da api, podendo ser importada no postman.


## Configuração do Ambiente

- Java 8
- Spring Boot 2.7.16
- Gradle
- MongoDB

## Como Executar

1. Clone o repositório.
2. Configure as propriedades do banco de dados no arquivo `application.yml`.
3. O banco de dados, pode ser provisionado a partir do arquivo `docker-compose.yml`
```
docker-compose up -d
```
. *Certifique-se de ter o Docker e o Docker Compose instalados em sua máquina.
4. Execute o projeto usando sua IDE ou através do comando `./mvnw spring-boot:run`.
```
./mvnw spring-boot:run
```
