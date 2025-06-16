# ⚖️ equals-concilia

Aplicação full-stack de conciliação financeira desenvolvida para o processo seletivo da Equals. A solução automatiza a leitura de arquivos de extrato, interpreta os dados, armazena as transações em um banco de dados e exibe um relatório interativo com filtros dinâmicos em uma interface web.

---

## 📚 Índice

- Tecnologias e Requisitos
- Instalação de Ferramentas
- Como Executar
  - [1. Clonar o Repositório](#1-clonar-o-repositório)
  - [2. Backend (Spring Boot)](#2-backend-spring-boot)
  - [3. Acessar Banco de Dados H2](#3-acessar-banco-de-dados-h2)
  - [4. Frontend (ReactVite)](#4-frontend-reactvite)
- Arquitetura do Projeto
- Desenvolvedor

---

## 🧰 Tecnologias e Requisitos

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
```
Para sistemas Windows ou Linux:
```bash
./mvnw clean install
./mvnw spring-boot:run
```
ou
```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

Para sistemas macOS com Apple Silicon (M1/M2):
Em alguns casos, é necessário especificar o caminho correto do JAVA_HOME:
```bash
sudo JAVA_HOME=$(/usr/libexec/java_home) sh ./mvnw clean install
sudo JAVA_HOME=$(/usr/libexec/java_home) sh ./mvnw spring-boot:run
```
> 💡 Explicação: Em Macs com chip Apple (Silicon), o Maven Wrapper às vezes não encontra automaticamente a versão correta do Java. Usar JAVA_HOME=$(/usr/libexec/java_home) garante que ele aponte para a versão instalada via SDKMAN ou JDK oficial.

> A API estará disponível em: [http://localhost:8080](http://localhost:8080)  
> - Endpoint de listagem: [http://localhost:8080/api/transacoes](http://localhost:8080/api/transacoes)  
> - Console do banco de dados H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
>
> As transações são carregadas automaticamente do arquivo `arquivo.txt` ao iniciar o sistema, caso o banco esteja vazio.

### 3. Acessar Banco de Dados (H2)

Acesse em: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

- JDBC URL: jdbc:h2:file:./data/concilia-db
- User: sa
- Password: (deixe em branco)

Execute:

```sql
SELECT * FROM transacao;
SELECT * FROM header;
SELECT * FROM trailer;
```

### 4. Frontend (React/Vite)

Verifique se o backend está em execução em http://localhost:8080. Em seguida, rode o frontend:
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
│   ├── pom.xml                                # Gerenciador de dependências Maven
│   ├── src/
│   │   └── main/
│   │       ├── java/com/equals/concilia/
│   │       │   ├── config/
│   │       │   │   └── StartupRunner.java            # Executa a leitura automática do arquivo .txt na inicialização
│   │       │   ├── controller/
│   │       │   │   ├── ParserController.java         # Endpoints REST para listar e filtrar transações
│   │       │   │   └── HomeController.java           # Página HTML simples com links úteis da API
│   │       │   ├── model/
│   │       │   │   ├── Header.java                   # Modelo JPA para a linha do tipo header
│   │       │   │   ├── Trailer.java                  # Modelo JPA para a linha do tipo trailer
│   │       │   │   └── Transacao.java                # Modelo JPA principal, representa uma venda (linha detalhe)
│   │       │   ├── repository/
│   │       │   │   ├── HeaderRepository.java         # Interface JPA para persistência do header
│   │       │   │   ├── TrailerRepository.java        # Interface JPA para persistência do trailer
│   │       │   │   └── TransacaoRepository.java      # Interface JPA para persistência das transações
│   │       │   ├── service/
│   │       │   │   └── ArquivoParserService.java     # Serviço que faz a leitura, parsing e salvamento dos dados
│   │       │   └── ConciliaApplication.java          # Classe principal da aplicação Spring Boot
│   │       └── resources/
│   │           ├── application.properties            # Configuração do banco H2, console e Flyway
│   │           ├── db/migration/
│   │           │   ├── V1__create_transacao_table.sql        # Script Flyway para criar a tabela de transações
│   │           │   └── V2__create_header_trailer_tables.sql  # Script Flyway para criar as tabelas de header e trailer
│   │           └── data/
│   │               └── arquivo.txt                   # Arquivo de exemplo com as transações brutas
│
├── frontend/
│   ├── App.jsx                              # Componente raiz da aplicação React
│   ├── main.jsx                             # Ponto de entrada da aplicação (ReactDOM.createRoot)
│   ├── pages/
│   │   └── ReportPage.jsx                   # Página principal: carrega filtros e exibe relatório
│   ├── components/
│   │   ├── Filter.jsx                       # Formulário com campos de filtro (data, valor, bandeira, etc.)
│   │   └── TransactionsTable.jsx           # Tabela que exibe os dados das transações
│   ├── api/
│   │   └── transactions.js                 # Funções que fazem requisições HTTP para a API backend
│   └── utils/
│       └── exportToPDF.js                  # Função utilitária para exportar o relatório de transações em PDF
│
└── README.md                                # Documentação e instruções de uso do projeto

```

---

## 🙋‍♂️ Desenvolvedor

* **Diogo Carrer** ([diogocarrer](https://github.com/diogocarrer))

