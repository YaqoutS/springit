package com.salameh.springit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import java.util.Arrays;

@SpringBootApplication
public class SpringitApplication {
    @Autowired
    private ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(SpringitApplication.class, args);
    }
    @Bean
    CommandLineRunner runner() {
        return args -> {
            System.out.println("----------------------------------------------------------");
            System.out.println("Printing out all the bean names in the application context");
            System.out.println("----------------------------------------------------------");
            String[] beans = applicationContext.getBeanDefinitionNames();
            Arrays.sort(beans);
            for(String bean: beans) {
                System.out.println(bean);
            }
        };
    }
}