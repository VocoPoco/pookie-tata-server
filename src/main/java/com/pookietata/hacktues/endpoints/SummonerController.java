package com.pookietata.hacktues.endpoints;

import com.pookietata.hacktues.clients.RiotApiClient;
import com.pookietata.hacktues.models.MatchHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api")
public class SummonerController {

    private final RiotApiClient riotApiClient;

    @Autowired
    public SummonerController(RiotApiClient riotApiClient) {
        this.riotApiClient = riotApiClient;
    }

    @PostMapping("/search")
    public MatchHistoryDto searchSummoner(@RequestParam("puuid") String puuid) {
        // Assuming getMatchHistoryAfter fetches the last 20 matches for simplicity
        return riotApiClient.getMatchHistoryAfter(puuid, Instant.now().minus(30, ChronoUnit.DAYS));
    }
}
