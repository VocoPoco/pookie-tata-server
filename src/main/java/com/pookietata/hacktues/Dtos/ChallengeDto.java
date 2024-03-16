package com.pookietata.hacktues.Dtos;


import com.pookietata.hacktues.models.enums.ChallengeStatus;
import com.pookietata.hacktues.models.enums.ChallengeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ChallengeDto {
    private Long id;
    private ChallengeType challengeType;
    private Integer rewardValue;
    private String name;
    private String description;
    private Date expirationTime;
    private Date startTime;
    private int entryFee;
    private ChallengeStatus challengeStatus;
    private Set<RewardDto> rewards;

    // Constructors, getters, and setters are provided by Lombok
}
