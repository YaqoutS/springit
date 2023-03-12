package com.salameh.springit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Link extends Auditable{

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String title;
    private String url;

    @OneToMany(mappedBy = "link")
    private List<Comment> comments = new ArrayList<>(); //we initialize it because we don't want it to point to null
}
