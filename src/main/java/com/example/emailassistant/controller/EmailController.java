package com.example.emailassistant.controller;

import com.example.emailassistant.dto.EmailGenerateRequest;
import com.example.emailassistant.dto.EmailReplyRequest;
import com.example.emailassistant.dto.EmailResponse;
import com.example.emailassistant.dto.EmailRewriteRequest;
import com.example.emailassistant.dto.EmailSummarizeRequest;
import com.example.emailassistant.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/generate")
    public EmailResponse generate(@RequestBody EmailGenerateRequest request) {
        String result = emailService.generateEmail(request.getPrompt());
        return new EmailResponse(result);
    }

    @PostMapping("/reply")
    public EmailResponse reply(@RequestBody EmailReplyRequest request) {
        String result = emailService.generateReply(request.getOriginalEmail(), request.getInstructions());
        return new EmailResponse(result);
    }

    @PostMapping("/summarize")
    public EmailResponse summarize(@RequestBody EmailSummarizeRequest request) {
        String result = emailService.summarizeEmail(request.getEmailContent());
        return new EmailResponse(result);
    }

    @PostMapping("/rewrite")
    public EmailResponse rewrite(@RequestBody EmailRewriteRequest request) {
        String result = emailService.rewriteEmail(request.getEmailContent(), request.getTone());
        return new EmailResponse(result);
    }
}
