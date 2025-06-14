# ⚖️ equals-concilia

**Conciliação Financeira** — Aplicação full-stack que automatiza a leitura de extratos, interpreta registros e exibe um relatório interativo na web com filtros dinâmicos.

---

## 📚 Índice

- [📝 Descrição](#📝-descrição)
- [🧰 Tecnologias & Requisitos](#🧰-tecnologias--requisitos)
- [🚀 Como Executar](#🚀-como-executar)
  - [1. Clonar o Repositório](#1-clonar-o-repositório)
  - [2. Backend (Spring Boot)](#2-backend-spring-boot)
  - [3. Frontend (React/Vite)](#3-frontend-reactvite)
  - [4. Acessar Banco de Dados (H2)](#4-acessar-banco-de-dados-h2)
- [🙋‍♂️ Desenvolvedor](#🙋‍♂️-desenvolvedor)
- [📌 Observações Finais](#📌-observações-finais)

---

## 📝 Descrição

Esta aplicação oferece uma solução completa para conciliação financeira:

1. 📂 **Leitura automatizada** do arquivo `arquivo.txt` no início da aplicação.
2. 🧠 **Parser inteligente** para Header, Transações e Trailer do extrato.
3. 🗃️ **Persistência em banco H2** (modo arquivo) com versionamento por Flyway.
4. 🌐 **Frontend em React** com relatório visual e filtros por data, valores e bandeira.

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

> A API estará disponível em: http://localhost:8080. As transações são carregadas automaticamente do arquivo arquivo.txt no startup.

### 3. Frontend (React/Vite)

```bash
cd frontend
npm install
npm run dev
```

> Acesse: http://localhost:5173

### 4. Acessar Banco de Dados (H2)

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

---

## 🙋‍♂️ Desenvolvedor

* **Diogo Carrer** ([diogocarrer](https://github.com/diogocarrer))
  
---

## 📌 Observações

* O arquivo de exemplo `arquivo.txt` está em `backend/src/main/resources/data/arquivo.txt`.
* Funciona em qualquer IDE ou direto no terminal, garantindo portabilidade.
