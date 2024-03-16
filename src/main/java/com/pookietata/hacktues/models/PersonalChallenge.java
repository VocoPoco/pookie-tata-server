package com.pookietata.hacktues.models;

import com.pookietata.hacktues.models.enums.ChallengeGoal;
import com.pookietata.hacktues.models.enums.ChallengeStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "personal_challenges")
public class PersonalChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "challenge_goal", nullable = false)
    private ChallengeGoal challengeGoal;

    @Column(name = "goal_value", nullable = false)
    private Integer goalValue;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private Integer reward;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(nullable = true)
    private Boolean completed = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private ChallengeStatus status = ChallengeStatus.UPCOMING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors, getters, and setters
}

