package com.example.emailassistant.dto;

import lombok.Data;

@Data
public class EmailRewriteRequest {
    private String emailContent;
    private String tone; // Professional, Friendly, Formal, Casual
}
