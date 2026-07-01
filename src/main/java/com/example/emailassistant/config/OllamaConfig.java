package com.example.emailassistant.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Builds the ChatClient used across the application.
 * Spring AI auto-configures OllamaChatModel from application.properties,
 * we just wrap it into a ChatClient here.
 */
@Configuration
public class OllamaConfig {

    @Bean
    public ChatClient chatClient(OllamaChatModel ollamaChatModel) {
        return ChatClient.builder(ollamaChatModel).build();
    }
}
