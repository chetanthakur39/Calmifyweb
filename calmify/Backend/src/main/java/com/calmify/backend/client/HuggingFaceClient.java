package com.calmify.backend.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HuggingFaceClient {

    private final WebClient webClient;

    @Value("${huggingface.api.key}")
    private String apiKey;

    @Value("${huggingface.api.url}")
    private String apiUrl;

    public HuggingFaceClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public String analyzeMood(String mood, String note) {
System.out.println("Calling Hugging Face...");
       String prompt = """
You are Calmify AI, a professional mental wellness assistant.

Analyze the user's mental state.

Mood:
%s

Note:
%s

Return ONLY valid JSON.

Do NOT return markdown.
Do NOT return explanation.
Do NOT write ```json.

Return exactly this structure:

{
  "primaryEmotion": "",
  "stressLevel": "",
  "possibleCause": "",
  "suggestions": [
    "",
    "",
    "",
    "",
    ""
  ]
}
""".formatted(mood, note);

        String request = """
                {
                  "model":"Qwen/Qwen2.5-7B-Instruct-Turbo",
                  "messages":[
                    {
                      "role":"system",
                      "content":"You are a mental wellness assistant."
                    },
                    {
                      "role":"user",
                      "content":"%s"
                    }
                  ]
                }
                """.formatted(prompt.replace("\"","\\\""));
System.out.println("REQUEST JSON:");
System.out.println(request);
        String response = webClient.post()
        .uri(apiUrl)
        .header("Authorization", "Bearer " + apiKey)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
        .retrieve()
        .bodyToMono(String.class)
        .block();

try {

    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = mapper.readTree(response);

    return root
            .path("choices")
            .get(0)
            .path("message")
            .path("content")
            .asText();

} catch (Exception e) {

    return "Unable to analyze mood.";

}
    }

    public String chat(String message) {

    String request = """
            {
              "model":"meta-llama/Llama-3.1-8B-Instruct",
              "messages":[
                {
                  "role":"system",
                  "content":"You are Calmify AI, a friendly mental wellness assistant."
                },
                {
                  "role":"user",
                  "content":"%s"
                }
              ]
            }
            """.formatted(message.replace("\"", "\\\""));

    try {
System.out.println("Calling Hugging Face...");
        String response = webClient.post()
                .uri(apiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();
System.out.println("RAW RESPONSE = " + response);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);

        return root.path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();

    } catch (Exception e) {

        e.printStackTrace();
        return "Unable to connect to AI.";

    }
}

}