package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.exceptions.EmailException;
import com.bandeira.school_report_online.model.User;
import com.bandeira.school_report_online.model.UserRole;
import com.bandeira.school_report_online.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    AuthorizationService authorizationService;

    User user = new User(
            UUID.randomUUID().toString(),
            "marcos@gmail.com",
            "marcos33",
            UserRole.USER
    );

    @Nested
    class loadByUserName{

        @Test
        @DisplayName("Must return user successfully")
        void MustReturnUserSuccessfully() {
            doReturn(user)
                    .when(userRepository)
                    .findByEmail(user.getUsername());

            var response = authorizationService.loadUserByUsername(user.getUsername());

            assertNotNull(response);
            assertEquals(user.getUsername(), response.getUsername());
            assertEquals(user.getPassword(), response.getPassword());
        }

        @Test
        @DisplayName("Should throw exception when not finding user")
        void ShouldThrowExceptionWhenNotFindingUser() {
            doThrow(new EmailException(""))
                    .when(userRepository)
                    .findByEmail(user.getEmail());

            assertThrows(EmailException.class,
                    () -> authorizationService.loadUserByUsername(user.getUsername()));
        }
    }
}