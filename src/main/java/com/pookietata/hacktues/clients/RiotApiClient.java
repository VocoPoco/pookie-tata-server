package com.pookietata.hacktues.clients;

import com.pookietata.hacktues.config.RiotApiConfig;
import com.pookietata.hacktues.models.MatchHistoryDto;
import com.pookietata.hacktues.models.SummonerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
public class RiotApiClient {

    private final RestTemplate restTemplate;
    private final RiotApiConfig config;

    @Autowired
    public RiotApiClient(RestTemplate restTemplate, RiotApiConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public SummonerDto getSummonerByPuuid(String puuid) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", config.getApiKey());
        String url = String.format("%s/summoner/v4/summoners/by-puuid/%s", config.getBaseUrl(), puuid);
        try {
            ResponseEntity<SummonerDto> response = restTemplate.exchange(
                    url, HttpMethod.GET, new HttpEntity<>(headers), SummonerDto.class);
            return response.getBody();
        } catch (Exception e) {
            // Implement appropriate error handling
            throw new RuntimeException("Failed to fetch summoner by PUUID: " + e.getMessage(), e);
        }
    }

    public MatchHistoryDto getMatchHistoryAfter(String puuid, Instant startTime) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", config.getApiKey());
        String url = String.format("%s/match/v5/matches/by-puuid/%s/ids?startTime=%d",
                config.getBaseUrl(), puuid, startTime.getEpochSecond());
        try {
            ResponseEntity<MatchHistoryDto> response = restTemplate.exchange(
                    url, HttpMethod.GET, new HttpEntity<>(headers), MatchHistoryDto.class);
            return response.getBody();
        } catch (Exception e) {
            // Implement appropriate error handling
            throw new RuntimeException("Failed to fetch match history after specified time: " + e.getMessage(), e);
        }
    }
}

