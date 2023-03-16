package com.salameh.springit;

import com.salameh.springit.domain.Comment;
import com.salameh.springit.domain.Link;
import com.salameh.springit.repository.CommentRepository;
import com.salameh.springit.repository.LinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringitApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringitApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringitApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository) {
        return args -> {
            Link link = new Link("Getting Started with Spring Boot 3", "https://thereatdanvega.com/spring-boot-3");
            linkRepository.save(link);

            Comment comment = new Comment("This Spring Boot 3 link is awesomne!", link);
            commentRepository.save(comment);
            link.addComment(comment);

            System.out.println("we just inserted a link and comment");
        };
    }
}
