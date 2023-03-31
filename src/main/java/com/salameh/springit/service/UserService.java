package com.salameh.springit.service;

import com.salameh.springit.domain.User;
import com.salameh.springit.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final MailService mailService;

    public UserService(RoleService roleService, UserRepository userRepository, MailService mailService) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.mailService = mailService;
        encoder = new BCryptPasswordEncoder(); // it is better to pass that in as a bean managed by spring instead of creating a new instance
    }

    public User register(User user) {
        String secret = "{bcrypt}" + encoder.encode(user.getPassword());
        user.setEnabled(false);
        user.setPassword(secret);
        user.setConfirmPassword(secret);
        user.addRole(roleService.findByName("ROLE_USER"));
        user.setActivationCode(UUID.randomUUID().toString());
        save(user);
        sendActivationEmail(user);
        return user;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    private void sendActivationEmail(User user) {
        mailService.sendActivationEmail(user);
    }

    public void sendWelcomeEmail(User user) {
        mailService.sendWelcomeEmail(user);
    }

    @Transactional
    public void saveUsers(User ... users) {
        for(User user : users) {
            logger.info("Saving user: " + user.getEmail());
            userRepository.save(user);
        }
    }

    public Optional<User> findByEmailAndActivationCode(String email, String activationCode) {
        return userRepository.findByEmailAndActivationCode(email,activationCode);
    }

}
