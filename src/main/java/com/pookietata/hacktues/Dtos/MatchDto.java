package com.pookietata.hacktues.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchDto {
    // Including all required fields
    private String gameId;
    private String platformId;
    private long gameCreation;
    private long gameDuration;
    private String queueType;
    private MatchInfoDto info;
    // ... additional fields as required
}
