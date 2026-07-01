package com.example.emailassistant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Holds all the business logic for talking to the AI model.
 * Each method builds a prompt from a template and calls the ChatClient.
 */
@Service
@RequiredArgsConstructor
public class EmailService {

    private final ChatClient chatClient;

    public String generateEmail(String prompt) {
        PromptTemplate template = new PromptTemplate("""
                Write a professional email based on the following request.
                Include a suitable subject line, a greeting, a clear body, and a closing.

                Request: {prompt}
                """);

        String userMessage = template.render(Map.of("prompt", prompt));

        return chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }

    public String generateReply(String originalEmail, String instructions) {
        PromptTemplate template = new PromptTemplate("""
                You are replying to the email below on behalf of the user.
                Write a clear, professional, well-structured reply.

                Original Email:
                {originalEmail}

                Reply Instructions:
                {instructions}
                """);

        String userMessage = template.render(Map.of(
                "originalEmail", originalEmail,
                "instructions", instructions
        ));

        return chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }

    public String summarizeEmail(String emailContent) {
        PromptTemplate template = new PromptTemplate("""
                Summarize the following email in a few concise sentences.
                Highlight the key points and any action items.

                Email:
                {emailContent}
                """);

        String userMessage = template.render(Map.of("emailContent", emailContent));

        return chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }

    public String rewriteEmail(String emailContent, String tone) {
        PromptTemplate template = new PromptTemplate("""
                Rewrite the following email using a {tone} tone.
                Keep the original meaning and key details, but adjust the wording and style.

                Email:
                {emailContent}
                """);

        String userMessage = template.render(Map.of(
                "emailContent", emailContent,
                "tone", tone
        ));

        return chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }
}
