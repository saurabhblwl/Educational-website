package com.example.emailassistant.dto;

import lombok.Data;

@Data
public class EmailReplyRequest {
    private String originalEmail;
    private String instructions;
}
