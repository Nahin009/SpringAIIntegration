package com.example.demo.controller;

import com.example.demo.service.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {


    private final ImageService stabilityApiService;

    public ImageController(ImageService stabilityApiService) {
        this.stabilityApiService = stabilityApiService;
    }

    @GetMapping("/generate")
    public byte[] generateImage() {
        try {
            return stabilityApiService.generateImage();
        } catch (IOException e) {
            e.printStackTrace();
    }
        return null;
    }

}
