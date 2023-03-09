package com.salameh.springit;

import com.salameh.springit.config.SpringitProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableConfigurationProperties(SpringitProperties.class) //let the spring know that we have a properties file, and we want it to be available
public class SpringitApplication {

    @Autowired
    private SpringitProperties springitProperties;

    public static void main(String[] args) {
        SpringApplication.run(SpringitApplication.class, args);
    }

    @Bean
    @Profile("dev")
    CommandLineRunner runner() {
        return args -> {
            System.out.println("Welcome message: " + springitProperties.getWelcomeMsg());
        };
    }

}
