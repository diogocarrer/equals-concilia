# ⚖️ equals-concilia

Aplicação full-stack de conciliação financeira desenvolvida para o processo seletivo da Equals. A solução automatiza a leitura de arquivos de extrato, interpreta os dados, armazena as transações em um banco de dados e exibe um relatório interativo com filtros dinâmicos em uma interface web.

---

## 📚 Índice

- [🧰 Tecnologias & Requisitos](#tecnologias--requisitos)
- [🛠️ Instalação de Ferramentas](#🛠instalação-de-ferramentas)
- [🚀 Como Executar](#como-executar)
  - [1. Clonar o Repositório](#1-clonar-o-repositório)
  - [2. Backend (Spring Boot)](#2-backend-spring-boot)
  - [3. Acessar Banco de Dados (H2)](#4-acessar-banco-de-dados-h2)
  - [4. Frontend (React/Vite)](#3-frontend-reactvite)
- [🏗️ Arquitetura do Projeto](#-arquitetura-do-projeto)
- [🙋‍♂️ Desenvolvedor](#🙋‍♂desenvolvedor)

---

## 🧰 Tecnologias & Requisitos

### 🔙 Backend

- Java 17
- Maven Wrapper (`./mvnw`)
- Spring Boot 3.5
- Spring Data JPA
- H2 Database (modo arquivo)
- Flyway (migração de schema)

### 🔜 Frontend

- Node.js 14+
- npm
- React 18 + Vite

### ⚙️ Gerais

- Git
- Terminal (funciona sem IDE)

---

## 🛠️ Instalação de Ferramentas (Pré-requisitos)

Antes de executar o projeto, é necessário garantir que as seguintes ferramentas estejam instaladas no seu ambiente:

### ✅ Java 17
- Necessário para rodar o backend com Spring Boot.
- Verifique com: `java -version`
- Download: https://adoptium.net/pt/temurin/releases/

### ✅ Node.js (v14 ou superior)
- Necessário para rodar o frontend com Vite.
- Verifique com: `node -v`
- Download: https://nodejs.org/

### ✅ npm (gerenciador de pacotes do Node)
- Já vem com o Node.js.
- Verifique com: `npm -v`

### ✅ Git
- Necessário para clonar o repositório.
- Download: https://git-scm.com/

> Todas as etapas de execução podem ser feitas via terminal, sem depender de uma IDE específica.

---

## 🚀 Como Executar

### 1. Clonar o Repositório

```bash
git clone https://github.com/diogocarrer/equals-concilia.git
cd equals-concilia
```

### 2. Backend (Spring Boot)

```bash
cd backend

# Unix/macOS ou Git Bash no Windows
./mvnw clean install
./mvnw spring-boot:run

# Windows (CMD)
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

> A API estará disponível em: [http://localhost:8080](http://localhost:8080)  
> - Endpoint de listagem: [http://localhost:8080/api/transacoes](http://localhost:8080/api/transacoes)  
> - Console do banco de dados H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
>
> As transações são carregadas automaticamente do arquivo `arquivo.txt` ao iniciar o sistema, caso o banco esteja vazio.

### 3. Acessar Banco de Dados (H2)

```bash
http://localhost:8080/h2-console
```

- JDBC URL: jdbc:h2:file:./data/concilia-db
- User: sa
- Password: (deixe em branco)

Execute:

```sql
SELECT * FROM transacao;
```

### 4. Frontend (React/Vite)

```bash
cd frontend
npm install
npm run dev
```

> Acesse: http://localhost:5173
> 
---
## 🏗️ Arquitetura do Projeto
O projeto está dividido em duas camadas principais: backend (API REST em Java) e frontend (interface web com Vite/React).

```bash
equals-concilia/
├── backend/
│   ├── pom.xml                          # Gerenciador de dependências Maven
│   ├── src/
│   │   └── main/
│   │       ├── java/com/equals/concilia/
│   │       │   ├── config/
│   │       │   │   └── StartupRunner.java            # Executa carga automática do arquivo .txt ao iniciar o sistema
│   │       │   ├── controller/
│   │       │   │   └── ParserController.java         # Endpoints REST para upload e visualização dos dados
│   │       │   ├── model/
│   │       │   │   ├── Header.java                   # Modelo para linha header do arquivo
│   │       │   │   ├── Trailer.java                  # Modelo para linha trailer
│   │       │   │   └── Transacao.java                # Entidade JPA principal da aplicação (representa uma venda)
│   │       │   ├── repository/
│   │       │   │   └── TransacaoRepository.java      # Interface JPA para persistência das transações
│   │       │   ├── service/
│   │       │   │   └── ArquivoParserService.java     # Serviço responsável por ler, interpretar e salvar os dados
│   │       │   └── ConciliaApplication.java          # Classe principal da aplicação Spring Boot
│   │       └── resources/
│   │           ├── application.properties            # Configuração do banco de dados e porta do servidor
│   │           ├── db/migration/
│   │           │   └── V1_create_transacao_table.sql # Script Flyway para criação da tabela no banco
│   │           └── data/
│   │               └── arquivo.txt                   # Arquivo de exemplo com dados brutos de transações
│
├── frontend/
│   ├── App.jsx                          # Componente principal da aplicação
│   ├── main.jsx                         # Ponto de entrada React (createRoot)
│   ├── pages/
│   │   └── ReportPage.jsx               # Página principal com filtro e relatório de transações
│   ├── components/
│   │   ├── Filter.jsx                   # Formulário de filtros para buscar transações
│   │   └── TransactionsTable.jsx       # Tabela com os dados das transações filtradas
│   └── api/
│       └── transactions.js             # Comunicação com a API backend via axios
│
└── README.md
```

---

## 🙋‍♂️ Desenvolvedor

* **Diogo Carrer** ([diogocarrer](https://github.com/diogocarrer))

