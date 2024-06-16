package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {
    @GetMapping("/")
    public String blabla() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/hello")
    public String helloRoute() {
        return "Hello World!";
    }

}
