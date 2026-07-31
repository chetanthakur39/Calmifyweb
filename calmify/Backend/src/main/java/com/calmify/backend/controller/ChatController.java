package com.calmify.backend.controller;

import com.calmify.backend.dto.ChatRequestDto;
import com.calmify.backend.dto.ChatResponseDto;
import com.calmify.backend.service.ChatService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponseDto chat(@RequestBody ChatRequestDto request) {

        String reply = chatService.chat(request.getMessage());

        return new ChatResponseDto(reply);
    }
}