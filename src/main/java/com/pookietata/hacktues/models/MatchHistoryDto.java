package com.pookietata.hacktues.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatchHistoryDto {
    private List<MatchDto> matches;
}
