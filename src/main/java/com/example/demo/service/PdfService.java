package com.example.demo.service;

import com.example.demo.DTO.pdf.template;
import com.example.demo.DTO.pdf.data;
import com.example.demo.DTO.pdf.documentRequest;
import com.fasterxml.jackson.databind.JsonNode;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PdfService {

    private final PdfJwtService pdfJwtService;
    private final String subject;

    private template template;
    private data data;
    private documentRequest documentRequest;

    public PdfService(PdfJwtService pdfJwtService, @Qualifier("PdfSubject") String subject) {
        this.pdfJwtService = pdfJwtService;
        this.subject = subject;

        this.documentRequest = new documentRequest();
        this.template = new template();
        this.data = new data();

        this.data.setTopic("ababa");
        this.data.setScore("2");
        this.data.setDate("ababa");
        this.data.setQ1("aaaa");
        this.data.setQ1o1("ababa");
        this.data.setQ1o2("ababa");
        this.data.setQ1o3("ababa");
        this.data.setQ1o4("ababa");
        this.data.setQ1des("ndjcndcjdncjncjsncjsdn");
        this.data.setQ1correctAns("c");
        this.data.setQ1ans("c");
        this.data.setQ1status("1");
        this.data.setQ2("bbbb");
        this.data.setQ2o1("ababa");
        this.data.setQ2o2("ababa");
        this.data.setQ2o3("ababa");
        this.data.setQ2o4("ababa");
        this.data.setQ2des("adskjn");
        this.data.setQ2correctAns("b");
        this.data.setQ2ans("c");
        this.data.setQ2status("-1");

        this.template.setData(this.data);
        this.template.setId("1094920");

        this.documentRequest.setTemplate(this.template);
        this.documentRequest.setFormat("pdf");
        this.documentRequest.setOutput("url");
        this.documentRequest.setName("Quiz Example");

    }

    public String GeneratePdf() {

        long expirationTime = 60000; // 60 seconds

        String token = pdfJwtService.generateToken(subject, expirationTime);

        // Serialize the document request to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = "";

        try {
            jsonBody = objectMapper.writeValueAsString(documentRequest);
        } catch (Exception e) {
            return "Error serializing the document request";
        }

        // Make the API call using Unirest
        HttpResponse<String> response = Unirest.post("https://us1.pdfgeneratorapi.com/api/v4/documents/generate")
                .header("content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(jsonBody)
//                .body("{\"template\":{\"id\":\"1094916\",\"data\":{\"Name\":\"sidratul muntaha\",\"DueDate\":\"2024-06-15\"}},\"format\":\"pdf\",\"output\":\"url\",\"name\":\"Certificate Example\"}")
                .asString();

        try {
            return processJsonResponse(response.getBody());
        } catch (Exception e) {
            return "Error processing the response url";
        }
    }

    private String processJsonResponse(String jsonResponse) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Parse JSON string to JsonNode
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Extract the URL from the response
        String url = jsonNode.path("response").asText();

        String cleanedUrl = url.replace("\\", "");

        return cleanedUrl;
    }

}
