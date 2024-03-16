package com.pookietata.hacktues.models.enums;

import lombok.Getter;

@Getter
public enum ChallengeGoal {
    KILLS("kills"),
    ASSISTS("assists"),
    BARON_KILLS("baronKills"),
    CS("totalMinionsKilled"),
    VISION_SCORE("visionScore"),
    GOLD_EARNED("goldEarned"),
    DAMAGE_DEALT("totalDamageDealtToChampions"),
    TURRET_KILLS("turretKills"),
    OBJECTIVE_KILLS("objectivesStolen"),
    BARON_KILL("baronKills"),
    DRAGON_KILL("dragonKills"),
    HERALD_KILL("neutralMinionsKilled"),
    INHIBITOR_KILL("inhibitorKills"),
    WIN("win");

    private final String jsonFieldName;

    ChallengeGoal(String jsonFieldName) {
        this.jsonFieldName = jsonFieldName;
    }

    public String getJsonFieldName() {
        return jsonFieldName;
    }

}
