package com.pookietata.hacktues.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatchHistoryDto {
    private List<MatchDto> matches;
}
