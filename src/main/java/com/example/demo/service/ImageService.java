package com.example.demo.service;

import com.example.demo.DTO.Image.Artifact;
import com.example.demo.DTO.Image.RequestPayload;
import com.example.demo.DTO.Image.ResponsePayload;
import com.example.demo.DTO.Image.TextPrompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ImageService {


    private final String STABILITY_AI_API_KEY;
    private final String STABILITY_AI_BASE_URL;

    private final RestTemplate restTemplate;

    public ImageService(@Qualifier("ImageAPiKey") String stabilityAiApiKey, @Qualifier("ImageBaseUrl") String stabilityAiBaseUrl, @Qualifier("restTemplate") RestTemplate restTemplate) {
        STABILITY_AI_API_KEY = stabilityAiApiKey;
        STABILITY_AI_BASE_URL = stabilityAiBaseUrl;
        this.restTemplate = restTemplate;
    }

    public byte[] generateImage() throws IOException {
        String url = STABILITY_AI_BASE_URL + "/v1/generation/stable-diffusion-xl-1024-v1-0/text-to-image";

        TextPrompt textPrompt = new TextPrompt("A lighthouse on a cliff");
        RequestPayload requestPayload = new RequestPayload(
                Collections.singletonList(textPrompt),
                7,
                1024,
                1024,
                1,
                30
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(STABILITY_AI_API_KEY);

        HttpEntity<RequestPayload> request = new HttpEntity<>(requestPayload, headers);

        ResponseEntity<ResponsePayload> response = restTemplate.exchange(url, HttpMethod.POST, request, ResponsePayload.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            List<Artifact> artifacts = Objects.requireNonNull(response.getBody()).getArtifacts();
            for (Artifact artifact : artifacts) {
                byte[] imageBytes = Base64.getDecoder().decode(artifact.getBase64());
                try (FileOutputStream fos = new FileOutputStream("Static/test.png")) {
                    fos.write(imageBytes);
                }
                return imageBytes;
            }
        } else {
            throw new RuntimeException("Failed to generate image: " + response.getStatusCode() + " " + response.getBody());
        }
        return null;
    }

//    @Value("${SPRING_AI_STABILITY_AI_API_KEY}")
//    private String apiKey;
//
//    public byte[] generateStabilityImage(String prompt) {
//
//        StabilityAiImageOptions options = new StabilityAiImageOptions();
//        options.setResponseFormat("image/png");
//
//        StabilityAiApi api = new StabilityAiApi(apiKey);
//
//        StabilityAiImageModel model = new StabilityAiImageModel(api);
//
//        String demoPrompt = "A beautiful sunset over the ocean.";
//        ImageResponse response = model.call(
//                new ImagePrompt(demoPrompt, options)
//        );
//
//        return response.getResult().getOutput().getB64Json().getBytes();
//
//
//    }
}
