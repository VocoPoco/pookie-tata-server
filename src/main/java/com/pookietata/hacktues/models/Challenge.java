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
@Table(name = "challenges")
public class Challenge {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @NotBlank
  @Column(nullable = false, name = "challenge_type")
  private ChallengeType challengeType;

  @NonNull
  @NotBlank
  @Column(name = "reward")
  private Integer rewardValue;

  @NonNull
  @NotBlank
  @Column(name = "name")
  private String name;

  @NonNull
  @NotBlank
  @Column(name = "description", length = 1024)
  private String description;

  @NonNull
  @NotBlank
  @Column(name = "expiration_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date expirationTime;

  @NonNull
  @NotBlank
  @Column(name = "start_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date startTime;

  @NonNull
  @NotBlank
  @Column(name = "entry_fee")
  private int entryFee;

  @NonNull
  @Column(name = "challenge_Status")
  private ChallengeStatus challengeStatus;

  @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Reward> rewards = new HashSet<>();


}

