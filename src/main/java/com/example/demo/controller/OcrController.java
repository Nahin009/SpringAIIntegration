package com.example.demo.controller;

import com.example.demo.service.OcrService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/ocr")
public class OcrController {

    private final OcrService ocrService;

    public OcrController(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @GetMapping("/parseImage")
    public String parseImageFromFile() {
        try {
            return ocrService.parseImageFromFile();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error encoding image to Base64";
        }
    }
}

