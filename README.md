Configurar banco application.properties:

spring.datasource.url=
spring.datasource.username=
spring.datasource.password=

Configurar Token Firebase application.properties:

firebase.config.json=

Configurar banco Docker compose:
SPRING_DATASOURCE_URL:
SPRING_DATASOURCE_USERNAME:
SPRING_DATASOURCE_PASSWORD:

POSTGRES_DB:
POSTGRES_USER:
POSTGRES_PASSWORD:

Configurar Token Firebase:

firebase.config.json=

Ajustar Docker Compose prometheus para rota de sua api
targets: ['8080']

Gerar container prometheus
docker run -d --name=prometheus --network=monitoring -p 9090:9090 -v "seu caminho ex:\sistema-notificacoes\sistema-notificacoes\prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus"

Gerar container grafana
docker run -d --name=grafana --network=monitoring -p 3000:3000 grafana/grafana

Gerar imagem projeto
docker build -t notficationimage .

Iniciar aplicação
docker-compose up -d


# Configuração do Banco de Dados no Docker

```bash
docker run --name notificacoes_container \
  -e POSTGRES_USER=notificacoes_user \
  -e POSTGRES_PASSWORD=minhasenha \
  -e POSTGRES_DB=notificacoes_db \
  -p 5432:5432 \
  -v notificacoes_data:/var/lib/postgresql/data \
  -d postgres:latest
```

### Principais Parâmetros:
- `--name notificacoes_container`: Nome do container Docker.
- `POSTGRES_USER`, `POSTGRES_PASSWORD`, `POSTGRES_DB`: Define o usuário, senha e nome do banco.
- `-p 5432:5432`: Mapeia a porta do container para a porta local, permitindo acesso ao PostgreSQL.
- `-v notificacoes_data:/var/lib/postgresql/data`: Cria um volume para garantir a persistência dos dados.

## Acessando o Banco de Dados
1. Abra o **DBeaver** e crie uma nova conexão para PostgreSQL.
2. Configure os seguintes parâmetros:
   - **Host**: localhost
   - **Porta**: 5432
   - **Database**: notificacoes_db
   - **Username**: notificacoes_user
   - **Password**: minhasenha
3. Clique em **Testar Conexão** e depois em **Concluir**.

> **Nota de Persistência**: Os dados são salvos no volume `notificacoes_data`, mantendo-se disponíveis mesmo que o container seja parado ou removido.

---

## Documentação Resumida: Tabela `usuarios` para Login

### Estrutura da Tabela `usuarios`

| Coluna         | Tipo          | Descrição                                            |
|----------------|---------------|------------------------------------------------------|
| id             | SERIAL        | Identificador único e autoincrementado de cada usuário. |
| nome           | VARCHAR(100)  | Nome completo do usuário (não nulo).                  |
| telefone       | VARCHAR(15)   | Número de telefone (único), usado para login.         |
| senha_hash     | VARCHAR(255)  | Hash da senha do usuário para segurança.              |
| admin          | BOOLEAN       | Define se é administrador (TRUE) ou usuário comum (FALSE). |
| data_criacao   | TIMESTAMP     | Data de criação do usuário (padrão: data atual).      |

### Notas de Segurança
- **senha_hash**: Armazena o hash criptografado da senha (ex.: bcrypt), não a senha em texto puro.
- **Identificação Única**: O número de telefone é o identificador exclusivo para cada usuário, evitando duplicidade.

---

## Estrutura das Tabelas

### Tabela `notificacoes`

| Coluna        | Tipo          | Descrição                                          |
|---------------|---------------|----------------------------------------------------|
| id            | SERIAL        | Identificador único da notificação.                |
| titulo        | VARCHAR(100)  | Título da notificação.                             |
| corpo         | TEXT          | Mensagem do conteúdo da notificação.               |
| tipo          | VARCHAR(20)   | Tipo da notificação (ex.: "INFORMATIVO", "ALERTA"). |
| data_criacao  | TIMESTAMP     | Data de criação da notificação.                    |
| enviada       | BOOLEAN       | Se a notificação foi enviada ou não.               |

---

### Tabela `usuarios_notificacoes`

| Coluna         | Tipo      | Descrição                                            |
|----------------|-----------|------------------------------------------------------|
| id             | SERIAL    | Identificador único do registro.                     |
| usuario_id     | INTEGER   | Referência ao ID do usuário que recebeu a notificação.|
| notificacao_id | INTEGER   | Referência ao ID da notificação.                     |
| data_envio     | TIMESTAMP | Data e hora em que a notificação foi enviada.        |
| lida           | BOOLEAN   | Se a notificação foi lida pelo usuário.              |

---

## Arquitetura

```
├── auth/
│
├── infrastructure/
│   ├── config/
│   ├── exception/
│   └── security/
│
├── notifications/
│   ├── Exceptions/
│   ├── model/
│   ├── presentation/
│   │   ├── controller/
│   │   └── dto/
│   ├── repository/
│   └── services/
│       ├── impl/ 
│       └── interfaces/ 
│
└── SistemaNotificacoesApplication
```

## Descrição dos Pacotes

- **auth**: Contém classes relacionadas à autenticação, como o gerenciamento de tokens e validação de credenciais dos usuários.
- **infrastructure**
  - **config**: Configurações gerais do sistema, como propriedades de segurança, configuração de beans, e outras definições de ambiente.
  - **exception**: Classes que gerenciam o tratamento global de exceções, incluindo mapeamento de erros comuns e respostas padronizadas para falhas.
  - **security**: Implementação da segurança da aplicação, incluindo lógica para autenticação JWT e controle de acesso.
- **notifications**
  - **Exceptions**: Exceções específicas do módulo de notificações, para erros de lógica ou validação relacionados a notificações.
  - **model**: Entidades do domínio relacionadas a notificações, como as classes que representam notificações e usuários que recebem notificações.
  - **presentation**
    - **controller**: Controladores REST responsáveis por expor as APIs de notificações aos clientes, manipulando requisições HTTP.
    - **dto**: Objetos de Transferência de Dados (DTOs) para facilitar a comunicação entre cliente e servidor, como requisições e respostas de notificações.
  - **repository**: Interfaces de repositório para acessar e manipular dados de notificações no banco de dados.
  - **services**
    - **impl**: Implementações dos serviços de notificações, contendo a lógica de negócios para manipulação de notificações.
    - **interfaces**: Interfaces dos serviços, definindo contratos e regras de negócio do módulo de notificações.

- **SistemaNotificacoesApplication**: Classe principal que inicia a aplicação Spring Boot.

---

## Tecnologias Utilizadas

- **Spring Security**: Gerenciamento de autenticação e autorização de usuários.
- **JWT (JSON Web Token)**: Autenticação baseada em token.
- **Spring Data JPA**: Simplifica operações CRUD com PostgreSQL.
- **Spring Boot Actuator**: Monitoramento da aplicação (status, memória, tempo de resposta).
- **Micrometer com Prometheus**: Exposição de métricas para monitoramento de desempenho.
- **Lombok**: Redução de código boilerplate nas classes.
- **Spring Validation (com Jakarta Validation)**: Validação de dados de entrada.
- **Swagger (Springdoc OpenAPI)**: Documentação interativa da API.
- **Firebase Admin SDK**: Integração com Firebase para autenticação e gerenciamento de permissões.
- **PostgreSQL JDBC Driver**: Conexão com PostgreSQL.
- **WebSockets (Spring WebSocket)**: Comunicação em tempo real para notificações.

---

## Razões para as Escolhas Feitas

### JWT como Mecanismo de Autenticação
- Facilita o escalonamento do sistema eliminando a necessidade de sessões no servidor.
- O cliente carrega o token em cada requisição, facilitando o controle de acesso seguro.

### Spring Security
- Oferece integração robusta e flexível para autenticação e autorização, incluindo JWT.

### Estrutura com Clean Architecture
- Isolamento de responsabilidades em camadas facilita a manutenção e escalabilidade do código.

### Lombok
- Reduz código boilerplate, permitindo foco na lógica de negócios.

### Spring Validation
- Valida automaticamente dados de entrada com anotações, garantindo segurança e integridade.

### Swagger
- Facilita o uso por desenvolvedores externos com documentação interativa.

### WebSockets
- Permite notificações em tempo real, ideal para sistemas que requerem atualizações contínuas.

---

## Firebase
Usado para notificações via push.

---

### WebSockets para Notificações em Tempo Real
- Comunicação bidirecional em tempo real, eliminando a necessidade de polling para atualizações.


