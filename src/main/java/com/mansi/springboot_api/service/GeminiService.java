package com.mansi.springboot_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;


@Service
public class GeminiService {

    private final WebClient webClient;
    private final String apiKey;

    // Spring will inject the builder and the value here
    public GeminiService(WebClient.Builder webClientBuilder, @Value("${gemini.api.key}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl("https://generativelanguage.googleapis.com").build();
        this.apiKey = apiKey;
    }

    public String askAI(String question) {
        // Use the model you want; gemini-1.5-flash is great for speed
        String endpoint =
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-lite:generateContent?key=" + apiKey;


        // Your payload structure is correct for the Gemini API
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", question)
                        })
                }
        );

        try {
            return webClient.post()
                    .uri(endpoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(this::extractTextFromResponse)
                    .block(); // .block() is fine for non-reactive Spring Boot (Servlet)
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String extractTextFromResponse(Map response) {
        try {
            List candidates = (List) response.get("candidates");
            Map firstCandidate = (Map) candidates.get(0);
            Map content = (Map) firstCandidate.get("content");
            List parts = (List) content.get("parts");
            Map firstPart = (Map) parts.get(0);

            String rawText = firstPart.get("text").toString();

            return cleanAnswer(rawText);

        } catch (Exception e) {
            return "Could not parse AI response.";
        }
    }

    private String cleanAnswer(String text) {

        if (text == null) return "";

        // remove markdown bold **Mumbai**
        text = text.replaceAll("\\*\\*", "");

        // remove new lines
        text = text.replace("\n", " ").trim();

        // If sentence contains "is", keep last part
        // Example: "The capital of Maharashtra is Mumbai."
        if (text.toLowerCase().contains(" is ")) {
            String[] parts = text.split(" is ");
            text = parts[parts.length - 1];
        }

        text = text.replaceAll("^[Tt]he ", "");

        text = text.replaceAll("[.!,]", "");

        text = text.trim();
        if (text.contains("(")) {
            text = text.substring(0, text.indexOf("(")).trim();
        }

        if (text.contains(" ")) {
            text = text.split(" ")[0];
        }

        return text;
    }


}