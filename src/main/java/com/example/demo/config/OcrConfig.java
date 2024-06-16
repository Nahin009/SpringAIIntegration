package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OcrConfig {

    @Value("${OCR_SPACE_AI_API_KEY}")
    private String apikey;

    @Value("https://api.ocr.space/parse/image")
    private String url;

    @Bean
    public String OCR_KEY() {
        return apikey;
    }

    @Bean
    public String OCR_URL() {
        return url;
    }
}
