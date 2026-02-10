package com.mansi.springboot_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String askAI(String question) {

        // same as axios endpoint
        String url = "https://generativelanguage.googleapis.com/v1/models/gemini-2.5-flash-lite:generateContent?key=" + apiKey;

        // EXACT prompt like NodeJS
        String prompt = "Answer the following question with ONLY the direct answer. "
                + "Do NOT include any extra text or explanation. Question: " + question;

        String body = """
        {
          "contents": [
            {
              "parts": [
                { "text": "%s" }
              ]
            }
          ]
        }
        """.formatted(prompt.replace("\"",""));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            String json = response.getBody();

            // Extract Gemini text response
            int index = json.indexOf("\"text\":");
            int start = json.indexOf("\"", index + 7) + 1;
            int end = json.indexOf("\"", start);

            return json.substring(start, end).trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "unavailable";
        }
    }
}
