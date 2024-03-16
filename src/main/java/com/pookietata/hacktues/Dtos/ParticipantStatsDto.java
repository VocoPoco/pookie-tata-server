package com.pookietata.hacktues.Dtos;

import com.pookietata.hacktues.models.enums.ChallengeGoal;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class ParticipantStatsDto {
    private int kills;
    private int assists;
    private int baronKills;
    private int totalMinionsKilled;
    private int visionScore;
    private int goldEarned;
    private int totalDamageDealtToChampions;
    private int turretKills;
    private int objectivesStolen;
    private int dragonKills;
    private int neutralMinionsKilled;
    private int inhibitorKills;
    private boolean win;

    public int getStatValue(ChallengeGoal goal) {
        switch (goal) {
            case KILLS:
                return Optional.of(this.kills).orElse(0);
            case ASSISTS:
                return Optional.of(this.assists).orElse(0);
            case BARON_KILLS:
            case BARON_KILL:
                return Optional.of(this.baronKills).orElse(0);
            case CS:
                return Optional.of(this.totalMinionsKilled).orElse(0);
            case VISION_SCORE:
                return Optional.of(this.visionScore).orElse(0);
            case GOLD_EARNED:
                return Optional.of(this.goldEarned).orElse(0);
            case DAMAGE_DEALT:
                return Optional.of(this.totalDamageDealtToChampions).orElse(0);
            case TURRET_KILLS:
                return Optional.of(this.turretKills).orElse(0);
            case OBJECTIVE_KILLS:
                return Optional.of(this.objectivesStolen).orElse(0);
            case DRAGON_KILL:
                return Optional.of(this.dragonKills).orElse(0);
            case HERALD_KILL:
                return Optional.of(this.neutralMinionsKilled).orElse(0);
            case INHIBITOR_KILL:
                return Optional.of(this.inhibitorKills).orElse(0);
            case WIN:
                return Optional.of(this.win).orElse(false) ? 1 : 0;

            default:
                return 0;
        }
    }
}
