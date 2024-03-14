package com.pookietata.hacktues.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Reward")
public class Reward {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @NotBlank
  @Column(name = "Value")
  private Integer rewardValue;

  @ManyToOne(fetch = FetchType.LAZY) // Lazy loading is often a sensible default
  @JoinColumn(name = "challenge_id") // This defines the foreign key column.
  private Challenge challenge;
}

