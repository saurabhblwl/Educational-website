# Smart Email Assistant

A small Spring Boot + Spring AI application that uses a local **Ollama** model to
generate, reply to, summarize, and rewrite emails.

## Tech Stack

- Java 26
- Spring Boot 3.4
- Spring AI (Ollama starter)
- Gradle
- Lombok

No database, no security, no Docker, no vector store, no agents — just plain REST
controllers talking to a local LLM through Spring AI's `ChatClient`.

## Project Structure

```
src/main/java/com/example/emailassistant
├── EmailAssistantApplication.java   # main class
├── config/
│   └── OllamaConfig.java            # builds the ChatClient bean
├── controller/
│   ├── EmailController.java         # /email/* endpoints
│   └── HealthController.java        # /health endpoint
├── service/
│   └── EmailService.java            # prompt templates + AI calls
└── dto/
    ├── EmailGenerateRequest.java
    ├── EmailReplyRequest.java
    ├── EmailSummarizeRequest.java
    ├── EmailRewriteRequest.java
    └── EmailResponse.java
```

## Prerequisites

1. **Java 26** installed and on your `PATH`.
2. **Ollama** installed and running locally: https://ollama.com
3. Pull the model used by this project (default is `llama3.1`):

   ```bash
   ollama pull llama3.1
   ```

4. Make sure Ollama is running (it usually starts automatically, or run):

   ```bash
   ollama serve
   ```

   By default Ollama listens on `http://localhost:11434`, which matches
   `application.properties`.

## Configuration

`src/main/resources/application.properties`:

```properties
spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.chat.options.model=llama3.1
```

Change the model name here if you want to use a different Ollama model
(e.g. `mistral`, `llama3.2`, etc.) — just make sure you've pulled it first.

## Running the Application

From the project root:

```bash
./gradlew bootRun
```

(If you don't have the Gradle wrapper jar, run `gradle wrapper` once with a
local Gradle install, or simply `gradle bootRun`.)

The app starts on **http://localhost:8080**.

## API Endpoints

### 1. Health Check

```
GET /health
```

Response: `Application Running`

### 2. Generate Email

```
POST /email/generate
Content-Type: application/json

{
  "prompt": "Ask my manager for 2 days of leave next week for a family event"
}
```

### 3. Reply to Email

```
POST /email/reply
Content-Type: application/json

{
  "originalEmail": "Hi, can you send me the Q3 report by Friday?",
  "instructions": "Confirm I will send it by Thursday instead"
}
```

### 4. Summarize Email

```
POST /email/summarize
Content-Type: application/json

{
  "emailContent": "Hi team, the deployment planned for tonight has been moved to tomorrow 10 AM due to a pending code review. Please update your calendars accordingly. Thanks, John"
}
```

### 5. Rewrite Email (with tone)

```
POST /email/rewrite
Content-Type: application/json

{
  "emailContent": "hey can u send the files asap i need them today",
  "tone": "Professional"
}
```

Supported tones: `Professional`, `Friendly`, `Formal`, `Casual`.

## Example curl

```bash
curl -X POST http://localhost:8080/email/generate \
  -H "Content-Type: application/json" \
  -d '{"prompt": "Ask my manager for 2 days of leave next week"}'
```

All endpoints return JSON in the form:

```json
{
  "result": "...generated text..."
}
```

## Notes

- All AI logic lives in `EmailService`, controllers only handle HTTP concerns.
- Prompts are built with Spring AI's `PromptTemplate`.
- The `ChatClient` bean is configured once in `OllamaConfig` and reused everywhere.
