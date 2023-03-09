package com.salameh.springit.repository;

import com.salameh.springit.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link,Long> {
    Link findByTitle(String title); //the spring will create that for us
}
