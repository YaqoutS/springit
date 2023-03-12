package com.salameh.springit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Vote extends Auditable{
    @Id
    @GeneratedValue
    private Long id;
    private int vote;

    // user
    // link
}
