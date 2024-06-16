package com.example.demo.controller;

import com.example.demo.service.GeminiApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class GeminiController {

    private final GeminiApiService geminiApiService;

    public GeminiController(GeminiApiService geminiApiService) {
        this.geminiApiService = geminiApiService;
    }

    @GetMapping("/generate-content")
    public Mono<String> generateContent(@RequestParam String text) {
        return geminiApiService.generateContent(text);
    }
}

