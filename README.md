# Smart Email Assistant

A simple email assistant built with **Spring Boot**, **Spring AI**, and **Ollama**. It uses a local LLM to generate, rewrite, summarize, and reply to emails through REST APIs.

## Tech Stack

* Java 26
* Spring Boot
* Spring AI
* Ollama
* Gradle

## Features

* Generate professional emails
* Reply to emails
* Summarize long emails
* Rewrite emails in different tones
* Health check endpoint

## Setup

1. Install and start Ollama.
2. Pull the model:

```bash
ollama pull llama3.1
```

3. Run the application:

```bash
./gradlew bootRun
```

The application runs on:

```
http://localhost:8080
```

## API Endpoints

The application provides the following REST APIs:

* **GET** `/health` – Checks whether the application is running.
* **POST** `/email/generate` – Generates a professional email from a given prompt.
* **POST** `/email/reply` – Creates a reply based on the original email and your instructions.
* **POST** `/email/summarize` – Summarizes long email content into a concise version.
* **POST** `/email/rewrite` – Rewrites an email in a different tone, such as Professional, Friendly, Formal, or Casual.

## Configuration

```properties
spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.chat.options.model=llama3.1
```

You can switch to another Ollama model by updating the model name in `application.properties`.

## Notes

This project is intended as a lightweight example of integrating **Spring AI** with **Ollama**. All AI-related logic is handled in the service layer, while the controllers expose simple REST endpoints.
