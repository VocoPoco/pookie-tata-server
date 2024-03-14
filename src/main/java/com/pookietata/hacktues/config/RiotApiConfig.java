package com.pookietata.hacktues.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class RiotApiConfig {

    @Value("${riot.api.key}")
    private String apiKey;

    @Value("${riot.api.url}")
    private String baseUrl;

}
