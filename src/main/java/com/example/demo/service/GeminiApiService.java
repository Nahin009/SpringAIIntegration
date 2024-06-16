package com.example.demo.service;
import com.example.demo.DTO.GeminiApiResponse;
import com.example.demo.model.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
public class GeminiApiService {

    private final WebClient.Builder webClientBuilder;
    private final String apiKey;

    public GeminiApiService(@Qualifier("webClientBuilder") WebClient.Builder webClientBuilder, @Qualifier("googleApiKey") String apiKey) {
        this.webClientBuilder = webClientBuilder;
        this.apiKey = apiKey;
    }

    public Mono<String> generateContent(String text) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey;

        String FullText = "Give me 2 " + text +" MCQs in JSON format as an array of objects: { question: string; option1: string; option2: string; option3: string; option4: string; correctOptionNo: int; solutionDescription: string; } dont say anything extra";

        String requestJson = "{ \"contents\": [{ \"parts\":[{ \"text\": \"" + FullText + "\"}]}]}";

        return webClientBuilder.build()
                .post()
                .uri(url)
                .header("Content-Type", "application/json")
                .bodyValue(requestJson)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    try {
                        return extractFromResponse(response);
                    } catch (JsonProcessingException e) {
                        return "Error processing response";
                    }
                })
                .map(response -> {
                    try {
                        return extractQuestions(response);
                    } catch (JsonProcessingException e) {
                        return "Error getting questions";
                    }
                });
    }

    private String extractFromResponse(String response) throws JsonProcessingException {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            GeminiApiResponse geminiApiResponse = objectMapper.readValue(response, GeminiApiResponse.class);
            return geminiApiResponse.getCandidates().get(0).getContent().getParts().get(0).getText();
        }catch (Exception e){
            return "Error extracting response";
        }
    }

    private String extractQuestions(String text) throws JsonProcessingException {
        String rawJsonText = text.trim();
        String jsonText = rawJsonText.replaceAll("^```json|```$", "").trim();
        System.out.println(jsonText);
//        return jsonText;

        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();

        try {
            List<Question> questions = objectMapper.readValue(jsonText, new TypeReference<List<Question>>() {});
            for (Question question : questions) {
                sb.append(question.toString());
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            return "Error mapping questions";
        }
        return sb.toString();
    }

}

