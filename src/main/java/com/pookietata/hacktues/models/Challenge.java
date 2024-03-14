package com.pookietata.hacktues.models;

import com.pookietata.hacktues.models.enums.ChallengeStatus;
import com.pookietata.hacktues.models.enums.ChallengeType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Challenges")
public class Challenge {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long challengeId;

  @NonNull
  @NotBlank
  @Column(nullable = false, name = "challenge_type")
  private ChallengeType challengeType;

  @NonNull
  @NotBlank
  @Column(name = "Reward")
  private Integer rewardValue;

  @NonNull
  @NotBlank
  @Column(name = "Name")
  private String name;

  @NonNull
  @NotBlank
  @Column(name = "Description", length = 1024)
  private String description;

  @NonNull
  @NotBlank
  @Column(name = "Expiration_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date expirationTime;

  @NonNull
  @NotBlank
  @Column(name = "Start_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date startTime;

  @NonNull
  @NotBlank
  @Column(name = "Entry_fee")
  private int entryFee;

  @NonNull
  @Column(name = "Challenge_Status")
  private ChallengeStatus challengeStatus;

  @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Reward> rewards = new HashSet<>();


}

