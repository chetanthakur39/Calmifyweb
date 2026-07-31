package com.calmify.backend.service.impl;

import com.calmify.backend.service.MoodDetectionService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MoodDetectionServiceImpl implements MoodDetectionService {

    private final WebClient webClient;

    @Value("${huggingface.api.key}")
    private String apiKey;

    @Value("${huggingface.api.url}")
    private String apiUrl;

    public MoodDetectionServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String detectMood(String message) {

        String prompt = """
                Analyze the user's mood.

                User Message:
                %s

                Return ONLY one word from this list:
                HAPPY
                SAD
                ANGRY
                STRESSED
                ANXIOUS
                EXCITED
                """.formatted(message);

        String request = """
                {
                  "model":"Qwen/Qwen2.5-7B-Instruct-Turbo",
                  "messages":[
                    {
                      "role":"system",
                      "content":"You are an emotion detection AI."
                    },
                    {
                      "role":"user",
                      "content":"%s"
                    }
                  ]
                }
                """.formatted(prompt.replace("\"", "\\\""));

        try {

            String response = webClient.post()
                    .uri(apiUrl)
                    .header("Authorization", "Bearer " + apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            return root.path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText()
                    .trim();

        } catch (Exception e) {

            e.printStackTrace();
            return "UNKNOWN";

        }
    }
}