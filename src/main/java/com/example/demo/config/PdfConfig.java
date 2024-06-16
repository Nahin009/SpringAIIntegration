package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PdfConfig {

    @Value("${PDF_API_KEY}")
    private String pdfApiKey;

    @Value("${PDF_API_SECRET}")
    private String pdfApiSecret;

    @Value("${PDF_SUBJECT}")
    private String subject;

    @Bean
    public String PdfApiKey() {
        return pdfApiKey;
    }

    @Bean
    public String PdfApiSecret() {
        return pdfApiSecret;
    }

    @Bean
    public String PdfSubject() {
        return subject;
    }

}
