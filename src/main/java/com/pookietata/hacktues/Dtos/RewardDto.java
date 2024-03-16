package com.pookietata.hacktues.Dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RewardDto {
    private Long id;
    private String type;
    private String description;
    private Integer value;

    // Constructors, getters, and setters are provided by Lombok
}

