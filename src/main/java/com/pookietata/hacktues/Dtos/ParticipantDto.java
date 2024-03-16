package com.pookietata.hacktues.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantDto {
    // ... other participant fields

    private String puuid;
    private ParticipantStatsDto stats;

    // ... getters and setters
}

