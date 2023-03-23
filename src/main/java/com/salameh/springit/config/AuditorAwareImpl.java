package com.salameh.springit.config;

import com.salameh.springit.domain.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() { // get the logged-in user
        if(SecurityContextHolder.getContext().getAuthentication() == null ) { // in the development, we add links but there is no any logged-in user
            return Optional.of("admin@gmail.com");
        } else {
            return Optional.of(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
        }
    }
}
