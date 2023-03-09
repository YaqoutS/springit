package com.salameh.springit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Link {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String title;
    private String url;

    // comments
    @OneToMany(mappedBy = "link")
    private List<Comment> comments;
}
