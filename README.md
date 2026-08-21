# StudyLog

---

API inteligente para registro de estudos utilizando Spring Boot e Spring AI.

O StudyLog permite registrar anotações de estudo a partir de áudios. A aplicação utiliza IA para interpretar o áudio do usuário e, por meio de Tool Calling, executar uma função real da aplicação responsável por registrar a informação no banco de dados.

## Funcionalidades

- Recebe resumos de estudos por áudio;
- Transcreve o áudio para texto;
- Interpreta instruções utilizando IA;
- Utiliza Tool Calling para execução de funções;
- Cria registros de estudo;
- Persiste os registros em PostgreSQL;
- Consulta e exclui registros através de API REST;

## Fluxo da aplicação

```
Áudio do usuário
       ↓
Transcrição
       ↓
Spring AI
       ↓
Interpretação da intenção
       ↓
Tool Calling
       ↓
PostgreSQL
       ↓
Resposta para o usuário
```

### Exemplo


O usuário envia um áudio dizendo:

```
"Hoje estudei Docker e aprendi a dockerizar minha aplicação Java."
```
A IA interpreta a mensagem e aciona a ferramenta responsável por criar uma nova anotação de estudo.

## Tecnologias Utilizadas
- Java 25
- Spring Boot 4.1.0
- Spring AI 2.0.0
- Google Gemini
- Spring Data JPA
- PostgreSQL 17
- Docker
- Docker Compose
- Maven
- Lombok
- Springdoc OpenAPI / Swagger

## Como executar

### Pré-requisitos

Para executar o projeto, é necessário:
- Java 25
- Maven
- Docker
- API Key do Google Gemini

### Setup

Crie um arquivo .env na raiz do projeto:
```
DB_USERNAME=postgres 
DB_PASSWORD=postgres 
GOOGLE_API_KEY=sua-chave-do-gemini
```

### Como rodar a aplicação

Configure o arquivo .env e execute:

```
mvnw.cmd spring-boot:run
```

Após iniciar a aplicação, a documentação pode ser acessada através do Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

A partir da interface do Swagger é possível visualizar e testar os endpoints da aplicação.

## O que aprendi

Durante o desenvolvimento do projeto, aprofundei meus conhecimentos em:

- Configuração do Spring AI;
- Integração com modelos de linguagem;
- ChatClient;
- Tool Calling;
- Transcrição de áudio em texto;
- Geração de voz a partir de texto;
- Integração entre IA e funções reais da aplicação;
- Construção de uma aplicação inteligente utilizando Spring Boot e Spring AI.

## Possíveis evoluções

- Consulta de estudos usando linguagem natural;
- Categorização dos estudos
- Estatísticas de tempo de estudo;
- Autenticação de usuários;
- Testes automatizados;
- Interface gráfica