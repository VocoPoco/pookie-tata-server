package com.pookietata.hacktues.clients;

import com.pookietata.hacktues.config.RiotApiConfig;
import com.pookietata.hacktues.models.SummonerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RiotApiClient {

    private final RestTemplate restTemplate;
    private final RiotApiConfig config;

    public RiotApiClient(RestTemplate restTemplate, RiotApiConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public SummonerDto getSummonerByName(String summonerName) {
        String url = String.format("%s/summoner/v4/summoners/by-name/%s", config.getBaseUrl(), summonerName);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", config.getApiKey());
        ResponseEntity<SummonerDto> response = restTemplate.getForEntity(url, SummonerDto.class, headers);
        return response.getBody();
    }

    // Add other methods to interact with different endpoints as needed
}
