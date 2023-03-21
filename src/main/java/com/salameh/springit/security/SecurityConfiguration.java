package com.salameh.springit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private UserDetailsServiceImpl userDetailsService;

    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                //.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/link/submit").hasRole("ADMIN");
                    //auth.requestMatchers("/resources/**").permitAll();
                    //auth.requestMatchers("/css/**", "/js/**", "images/**", "/libs.bootstrap/js/**", "/libs.bootstrap/css/**", "/templates/link/**",  "/templates/layouts/**").permitAll();
                    //auth.requestMatchers("/css/**").permitAll(); //this enhanced the situation but still ..
                    //auth.requestMatchers("/*.css").permitAll();
                    auth.anyRequest().permitAll();
                })
                .formLogin(Customizer.withDefaults())
                .build();
        //or return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        boolean securityDebug = false;
        return (web) -> web.debug(securityDebug)
                .ignoring()
                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }

    @Bean
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

}
