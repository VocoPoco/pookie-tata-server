package com.pookietata.hacktues.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchDto {
    private String gameId;
    private String platformId;
    private long gameCreation;
    private long gameDuration;
    private String queueType;
    // ... other relevant fields
}
