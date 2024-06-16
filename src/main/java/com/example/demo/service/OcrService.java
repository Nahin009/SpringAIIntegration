package com.example.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Service
public class OcrService {

    private final RestTemplate restTemplate;

    private final String apikey;
    private final String url;

    public OcrService(@Qualifier("restTemplate") RestTemplate restTemplate, @Qualifier("OCR_KEY") String apikey, @Qualifier("OCR_URL") String url) {
        this.restTemplate = restTemplate;
        this.apikey = apikey;
        this.url = url;
    }

    public String parseImageFromBase64(String base64Image) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("apikey", apikey);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("base64Image", "data:image/jpeg;base64," + base64Image);
        body.add("base64Image", "data:application/pdf;base64," + base64Image);
        body.add("language", "eng");
        body.add("isOverlayRequired", "false");
        body.add("OCREngine", "2");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        return extractParsedText(response.getBody());
    }

    public String parseImageFromFile() throws IOException {
        String base64Image = encodeImageToBase64();
        return parseImageFromBase64(base64Image);
    }

    private static String encodeImageToBase64() throws IOException {
        String dummyHandWrittenImagePath = "Static/test.jpg";
        String dummyCertificateImagePath = "Static/certificate.jpg";
        String dummyPDFPath = "Static/Quiz_Example.pdf";
        File file = new File(dummyPDFPath);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }

    private String extractParsedText(String jsonResponse) {
        try {
            JsonNode root = new ObjectMapper().readTree(jsonResponse);
            JsonNode parsedResults = root.path("ParsedResults");
            if (parsedResults.isArray() && parsedResults.size() > 0) {
                JsonNode parsedTextNode = parsedResults.get(0).path("ParsedText");
                return parsedTextNode.asText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error parsing response";
    }
}

