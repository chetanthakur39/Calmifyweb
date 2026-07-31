package com.calmify.backend.service.impl;

import com.calmify.backend.client.HuggingFaceClient;
import com.calmify.backend.service.ChatService;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final HuggingFaceClient huggingFaceClient;

    public ChatServiceImpl(HuggingFaceClient huggingFaceClient) {
        this.huggingFaceClient = huggingFaceClient;
    }

    @Override
    public String chat(String message) {

        return huggingFaceClient.chat(message);

    }
}