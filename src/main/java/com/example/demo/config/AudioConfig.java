package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class AudioConfig {
    @Value("${ASSEMBLY_AI_API_KEY}")
    private String ASSEMBLY_AI_API_KEY;

    @Bean
    public String getASSEMBLY_AI_API_KEY() {
        return ASSEMBLY_AI_API_KEY;
    }

}
