package com.example.demo.controller;

import com.example.demo.service.PdfService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    // Add a route that will generate a PDF file
    @GetMapping("/generate")
    public String GeneratePdf() {
        return pdfService.GeneratePdf();
    }
}
