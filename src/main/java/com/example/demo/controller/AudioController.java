package com.example.demo.controller;

import com.example.demo.service.AudioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/audio")
public class AudioController {

    private final AudioService audioService;

    public AudioController(AudioService audioService) {
        this.audioService = audioService;
    }

    @GetMapping("/transcribe")
    public Optional<String> transcribe() {
        return audioService.createTranscript();
    }
}
