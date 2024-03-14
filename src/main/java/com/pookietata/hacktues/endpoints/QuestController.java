package com.pookietata.hacktues.endpoints;

import com.pookietata.hacktues.clients.RiotApiClient;
import com.pookietata.hacktues.models.SummonerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quests")
public class QuestController {

    private final RiotApiClient riotApiClient;

    @Autowired
    public QuestController(RiotApiClient riotApiClient) {
        this.riotApiClient = riotApiClient;
    }

    @GetMapping("/summoner/{summonerName}")
    public ResponseEntity<SummonerDto> getSummonerInfo(@PathVariable String summonerName) {
        SummonerDto summonerDto = riotApiClient.getSummonerByName(summonerName);
        return ResponseEntity.ok(summonerDto);
    }

    // Add additional endpoints to handle other quests as needed
}
