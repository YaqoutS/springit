package com.salameh.springit.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
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
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(EndpointRequest.to("info")).permitAll();
                    auth.requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ACTUATOR");
                    auth.requestMatchers("/actuator/").hasRole("ACTUATOR");
                    auth.requestMatchers("/link/submit").hasRole("USER");
                    auth.requestMatchers("/link/**").permitAll();
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/h2-console/**").permitAll();
                    auth.anyRequest().permitAll();
                })
                .formLogin(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .headers().disable()
                .build();
        //or return http.build();
    }

}
