
---

# 📄 DocChat

### AI-Powered Document Question Answering using Spring AI, Ollama & PGVector

DocChat is a **Retrieval-Augmented Generation (RAG)** web application that allows users to upload documents, store them as vector embeddings in **PostgreSQL (PGVector)**, and ask natural language questions that are answered by a **locally hosted LLM (Ollama – Mistral)**.

This project demonstrates **enterprise-grade RAG architecture** using **Spring Boot** and **Spring AI**.

---

## 🚀 Features

* 📄 Upload plain-text documents
* 🧠 Generate embeddings using **Ollama (Mistral)**
* 🗄️ Store embeddings in **PostgreSQL with PGVector**
* 🔍 Semantic similarity search for relevant document chunks
* 🤖 AI-generated answers grounded in retrieved content
* 🌐 Simple HTML frontend
* 🐳 Dockerized database setup

---

## 🧱 Tech Stack

| Layer            | Technology            |
| ---------------- | --------------------- |
| Backend          | Spring Boot 3         |
| AI Framework     | Spring AI             |
| LLM              | Ollama (Mistral)      |
| Vector DB        | PostgreSQL + PGVector |
| Frontend         | HTML + JavaScript     |
| Containerization | Docker Compose        |

---

## 📁 Project Structure

```
DocChat/
├── pom.xml
├── compose.yaml
├── src/
│   └── main/
│       ├── java/com/docchat/
│       │   ├── DocChatApplication.java
│       │   ├── controller/ChatController.java
│       │   └── service/DocumentIngestionService.java
│       └── resources/
│           ├── application.properties
│           └── static/index.html
```

---

## ⚙️ Configuration

### `application.properties`

```properties
spring.application.name=DocChat

spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.chat.options.model=mistral
spring.ai.vectorstore.pgvector.initialize-schema=true

spring.datasource.url=jdbc:postgresql://localhost:5432/vectordb
spring.datasource.username=testuser
spring.datasource.password=testpwd
```

---

## 🐳 Docker Setup (PGVector)

### `compose.yaml`

```yaml
services:
  pgvector:
    image: pgvector/pgvector:pg16
    ports:
      - "5432:5432"
    environment:
      POSTGRES_DB: vectordb
      POSTGRES_USER: testuser
      POSTGRES_PASSWORD: testpwd
```

---

## ▶️ How to Run

### 1️⃣ Start PostgreSQL + PGVector

```bash
docker compose up -d
```

### 2️⃣ Start Ollama

```bash
ollama run mistral
```

### 3️⃣ Run Spring Boot Application

```bash
./mvnw spring-boot:run
```

### 4️⃣ Open in Browser

```
http://localhost:8080/index.html
```

---

## 🧠 How It Works (RAG Flow)

1. User uploads a document
2. Document is embedded using Ollama
3. Embeddings are stored in PGVector
4. User asks a question
5. Relevant chunks are retrieved via similarity search
6. `QuestionAnswerAdvisor` injects context into the LLM prompt
7. AI generates a grounded answer

---

## 📌 Key Spring AI Component

```java
.advisors(new QuestionAnswerAdvisor(vectorStore))
```

This automatically:

* Performs similarity search
* Retrieves relevant document chunks
* Grounds the LLM response

---

## 🎓 Use Cases

* Document Q&A systems
* Knowledge base assistants
* Research paper exploration
* Enterprise RAG prototypes
* AI-powered internal tools

---

## 🔮 Future Enhancements

* PDF upload support
* Multiple document management
* Metadata-based filtering
* Chat history / memory
* React frontend

---

## 👨‍💻 Author

**Pushparaj L**
B.Tech Cybersecurity & IoT
Spring Boot | AI | Cloud | Security

---

## 📜 License

This project is licensed under the **MIT License**.

---
