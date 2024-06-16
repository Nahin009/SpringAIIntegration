package com.example.demo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PdfJwtService {

    private final String apiKey;
    private final String apiSecret;

    public PdfJwtService(@Qualifier("PdfApiKey") String apiKey, @Qualifier("PdfApiSecret") String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public String generateToken(String subject, long expirationTime) {
        return Jwts.builder()
                .setIssuer(apiKey)         // Set the issuer to your API key
                .setSubject(subject)       // Set the subject (workspace identifier)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, apiSecret.getBytes()) // Sign with your API secret
                .compact();
    }
}

