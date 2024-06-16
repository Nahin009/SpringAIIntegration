package com.example.demo.DTO.pdf;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class documentRequest {
    private template template;
    private String format;
    private String output;
    private String name;
}
