package com.salameh.springit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Vote extends Auditable{
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private int vote;

    // user
    // link
}
