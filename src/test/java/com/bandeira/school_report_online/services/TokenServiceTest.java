package com.bandeira.school_report_online.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bandeira.school_report_online.model.User;
import com.bandeira.school_report_online.model.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {


    @Mock
    TokenService tokenService;



    @Nested
    class generateToken {

        User user = new User(
                UUID.randomUUID().toString(),
                "Pedro",
                "1234",
                UserRole.USER
        );

        @Test
        @DisplayName("Must create token successfully")
        void MustCreateTokenSuccessfully() {
            Algorithm algorithm = Algorithm.HMAC256("mock secret");
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);
            doReturn(token)
                    .when(tokenService)
                    .generateToken(user);

            var response = tokenService.generateToken(user);

            assertNotNull(response);
        }

        @Test
        @DisplayName("Must throw exception when generating token")
        void MustThrowExceptionWhenGeneratingToken() {
            doThrow(new RuntimeException())
                    .when(tokenService)
                    .generateToken(user);

            assertThrows(RuntimeException.class,
                    () -> tokenService.generateToken(user));
        }
    }

    @Nested
    class validateToken {

        User user = new User(
                UUID.randomUUID().toString(),
                "Pedro",
                "1234",
                UserRole.USER
        );

        @Test
        @DisplayName("Must validate exception successfully")
        void MustValidateExceptionSuccessfully() {
            Algorithm algorithm = Algorithm.HMAC256("mock secret");
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);

            JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

            doReturn("")
                    .when(tokenService)
                    .validateToken(token);

            var response = tokenService.validateToken(token);

            assertNotNull(response);
            assertEquals("", response);
        }

        @Test
        @DisplayName("Must throw exception when validating token")
        void MustThrowExceptionWhenValidatingToken() {

            Algorithm algorithm = Algorithm.HMAC256("mock secret");
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);

            doThrow(new JWTVerificationException("Erro criando token"))
                    .when(tokenService)
                    .validateToken(token);

            assertThrows(JWTVerificationException.class,
                    () -> tokenService.validateToken(token));
        }
    }

    @Nested
    class getExpirationDate {

        @Test
        @DisplayName("Must launch token expiry time successfully")
        void MustLaunchTokenExpiryTimeSuccessfully() {

            Instant instant = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));

            doReturn(instant)
                    .when(tokenService)
                    .getExpirationDate();

            var response = tokenService.getExpirationDate();

            assertEquals(response, instant);
        }
    }
}
