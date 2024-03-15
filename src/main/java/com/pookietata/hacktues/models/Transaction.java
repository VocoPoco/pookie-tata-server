package com.pookietata.hacktues.models;

import com.pookietata.hacktues.models.enums.TransactionType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transactionId;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;


  @NonNull
  @NotBlank
  @Column(name = "transaction_amount")
  private Integer amount;

  @NonNull
  @NotBlank
  @Column(name = "transaction_timestamp")
  @Temporal(TemporalType.TIMESTAMP)
  private Date timestamp;

  @NonNull
  @NotBlank
  @Column(name = "transaction_type")
  private TransactionType transactionType;

}

