package br.com.agls.expensesauthapi.domain.service.impl;

import br.com.agls.expensesauthapi.domain.entity.user.User;
import br.com.agls.expensesauthapi.domain.exceptions.RegisterUserException;
import br.com.agls.expensesauthapi.domain.service.RegisterService;
import br.com.agls.expensesauthapi.infra.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class RegisterServiceImpl implements RegisterService {
    
    private final UserRepository userRepository;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServiceImpl.class);
    
    @Override
    public void execute(User user) {
        try {
        LOGGER.info("Registering user: {}", user.getEmail());
        verifyIfUserExists(user.getEmail());

        userSetup(user);

        this.userRepository.save(user);
        } catch (Exception e) {
            LOGGER.error("Could not register user. Error: {}", e.getMessage());
            throw new RegisterUserException("Could not register user. Error: " + e.getMessage());
        }
    }

    private void userSetup(User user) {
        user.setPassword(encryptPassword(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
    }

    private void verifyIfUserExists(String email) {
        this.userRepository.findByEmail(email)
                .ifPresent(user -> {
                    LOGGER.error("Could not register user. User already exists. Email: {}", email);
                    throw new EntityNotFoundException("User already exists, email: " + email);
                });
    }
    
    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
