package br.com.agls.expensesauthapi.config.security;

import br.com.agls.expensesauthapi.domain.entity.user.User;
import br.com.agls.expensesauthapi.domain.exceptions.GenerateTokenException;
import br.com.agls.expensesauthapi.domain.exceptions.InvalidJWTException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

@Service
public class TokenService {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${api.jwt.secret}")
    private String secret;

    @Value("${api.jwt.expiration-time}")
    private Integer expirationTime;

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer(appName)
                    .withPayload(generatePayload(user))
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            LOGGER.error("Could not generate token. Error: {}", e.getMessage());
            throw new GenerateTokenException(e.getMessage());
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("app-name").build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            LOGGER.error("Invalid token. Error: {}", e.getMessage());
            throw new InvalidJWTException(e.getMessage());
        }
    }

    private Map<String, ?> generatePayload(User user) {
        return Map.of("userId", user.getId().toString(), "role", user.getRole().toString());
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusMinutes(expirationTime).toInstant(ZoneOffset.of("-03:00"));
    }
}
