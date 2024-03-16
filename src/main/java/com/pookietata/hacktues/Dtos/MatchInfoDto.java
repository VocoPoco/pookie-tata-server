package com.pookietata.hacktues.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatchInfoDto {
    private String gameMode;
    private List<ParticipantDto> participants;


    // ... getters and setters
}

