package com.calmify.backend.dto;

public class ChatRequestDto {

    private String message;

    public ChatRequestDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}