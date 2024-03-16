package com.pookietata.hacktues.Dtos;

import com.pookietata.hacktues.models.PersonalChallenge;
import com.pookietata.hacktues.models.enums.ChallengeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChallengeProgressionDto {
    private Long id;
    private ChallengeType challengeType;
    private String name;
    private String description;
    private Integer currentProgress;
    private Integer goalValue;
    private boolean isCompleted;

//    private ChallengeProgressionDto convertChallengeToProgressionDto(PersonalChallenge challenge) {
//        ChallengeProgressionDto dto = new ChallengeProgressionDto();
//        dto.setId(challenge.getId());
//        dto.setChallengeType(challenge.getChallengeType()); // Assuming ChallengeType is included in PersonalChallenge
//        dto.setName(challenge.getDescription()); // Or any other appropriate field for the name
//        dto.setDescription(challenge.getDescription());
//        dto.setCurrentProgress(calculateCurrentProgress(challenge)); // This would be a method to calculate the current progress
//        dto.setGoalValue(challenge.getGoalValue());
//        dto.setCompleted(challenge.getCompleted());
//
//        return dto;
//    }

}
