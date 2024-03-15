package com.pookietata.hacktues.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SummonerDto {
    private String id;
    private String name;
    private int profileIconId;
    private long revisionDate;
    private int summonerLevel;
}