package com.bandeira.school_report_online.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bandeira.school_report_online.dtos.UpdateEmailDTO;
import com.bandeira.school_report_online.dtos.UpdatePasswordDTO;
import com.bandeira.school_report_online.dtos.UserRequest;
import com.bandeira.school_report_online.exceptions.EmailAlreadyExistsException;
import com.bandeira.school_report_online.exceptions.UserNotFoundException;
import com.bandeira.school_report_online.model.User;
import com.bandeira.school_report_online.model.UserRole;
import com.bandeira.school_report_online.repositories.UserRepository;
import com.bandeira.school_report_online.util.EmailTemplate;
import com.bandeira.school_report_online.util.RandomString;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;



import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailTemplate emailTemplate;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;


    @Nested
    class createUser {

        User user = new User(UUID.randomUUID().toString(), "mateus@gmail.com", "12142151dsd"
                , UserRole.USER);

        UserRequest userRequest = new UserRequest("carol@Gmail.com", "121261g2g61"
                , UserRole.ADMIN);

        @Test
        @DisplayName("Must create user successfully")
        void MustCreateUserSuccessfully() throws JsonProcessingException, MessagingException, UnsupportedEncodingException {

            var random = RandomString.generateRandomString(44);

            doReturn(null)
                    .when(userRepository)
                    .findByEmail(userRequest.email());
            doReturn(userRequest.password())
                    .when(passwordEncoder)
                    .encode(userRequest.password());
            doReturn(user)
                    .when(userRepository)
                    .save(userArgumentCaptor.capture());

            userService.createUser(userRequest);

            var userCaptured = userArgumentCaptor.getValue();

            assertEquals(userRequest.email(), userCaptured.getEmail());
            assertEquals(userRequest.password(), userCaptured.getPassword());

            verify(userRepository, times(1))
                    .findByEmail(userRequest.email());
            verify(passwordEncoder, times(1))
                    .encode(userRequest.password());
            verify(userRepository, times(1))
                    .save(userArgumentCaptor.capture());
        }

        @Test
        @DisplayName("Must return exception when the email already exists")
        void MustReturnExceptionWhenTheEmailAlreadyExists() {
            doReturn(user)
                    .when(userRepository)
                    .findByEmail(userRequest.email());

            assertThrows(EmailAlreadyExistsException.class, () ->
                    userService.createUser(userRequest));

            verify(userRepository, times(1))
                    .findByEmail(userRequest.email());
            verify(passwordEncoder, times(0))
                    .encode(userRequest.password());
            verify(userRepository, times(0))
                    .save(userArgumentCaptor.capture());
        }
    }


    @Nested
    class findByEmail {

        User user = new User(UUID.randomUUID().toString(), "mateus@gmail.com", "12142151dsd"
                , UserRole.USER);

        @Test
        @DisplayName("The user must be returned successfully")
        void TheUserMustBeReturnedSuccessfully() {
            doReturn(user)
                    .when(userRepository)
                    .findByEmail(user.getEmail());

            var response = userService.findByEmail(user.getEmail());

            assertNotNull(response);

        }

        @Test
        @DisplayName("Should throw exception when not finding the user")
        void ShouldThrowExceptionWhenNotFindingTheUser() {
            doReturn(null)
                    .when(userRepository)
                    .findByEmail(user.getEmail());

            assertThrows(UserNotFoundException.class,
                    () -> userService.findByEmail(user.getEmail()));
        }
    }


    @Nested
    class updateEmail {

        User user = new User(UUID.randomUUID().toString(), "mateus@gmail.com", "6w25fs2tf52"
                , UserRole.USER);

        UpdateEmailDTO updateEmailDTO = new UpdateEmailDTO(UUID.randomUUID().toString(), "6w25fs2tf52"
                ,"carlos@gmail.com");

        @Test
        @DisplayName("Must update email successfully")
        void MustUpdateEmailSuccessfully() {
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(updateEmailDTO.id());
            doReturn(user)
                    .when(userRepository)
                    .save(userArgumentCaptor.capture());

            userService.updateEmail(updateEmailDTO);

            var userCaptured = userArgumentCaptor.getValue();

            assertEquals(updateEmailDTO.email(), userCaptured.getEmail());
            assertEquals(updateEmailDTO.password(), userCaptured.getPassword());

            verify(userRepository, times(1))
                    .findById(updateEmailDTO.id());
            verify(userRepository,times(1))
                    .save(userArgumentCaptor.capture());
        }

        @Test
        @DisplayName("Should throw exception when not finding the user")
        void ShouldThrowExceptionWhenNotFindingTheUser() {
            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(updateEmailDTO.id());

            assertThrows(UserNotFoundException.class,
                    () -> userService.updateEmail(updateEmailDTO));

            verify(userRepository, times(1))
                    .findById(updateEmailDTO.id());
            verify(userRepository, times(0))
                    .save(user);
        }
    }


    @Nested
    class updatePassword {

        User user = new User(UUID.randomUUID().toString(), "mateus@gmail.com", "12142151dsd"
                , UserRole.USER);

        UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO(UUID.randomUUID().toString(),"12142151dsd"
        , "71ye2e62e2", "71ye2e62e2");

        @Test
        @DisplayName("must update password successfully")
        void MustUpdatePasswordSuccessfully() {
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(updatePasswordDTO.id());
            doReturn(user)
                    .when(userRepository)
                    .save(userArgumentCaptor.capture());

            userService.updatePassword(updatePasswordDTO);

            var userCaptured = userArgumentCaptor.getValue();

            assertEquals(user.getPassword(), userCaptured.getPassword());

            verify(userRepository, times(1))
                    .findById(updatePasswordDTO.id());
            verify(userRepository, times(1))
                    .save(user);
        }

        @Test
        @DisplayName("Should throw exception when not finding the user")
        void ShouldThrowExceptionWhenNotFindingTheUser() {
            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(updatePasswordDTO.id());

            assertThrows(UserNotFoundException.class,
                    () -> userService.updatePassword(updatePasswordDTO));

            verify(userRepository, times(1))
                    .findById(updatePasswordDTO.id());
            verify(userRepository, times(0))
                    .save(user);
        }
    }

    @Nested
    class findAll {

        User user = new User(UUID.randomUUID().toString(), "mateus@gmail.com", "12142151dsd"
                , UserRole.USER);

        @Test
        @DisplayName("Must return users successfully")
        void MustReturnUsersSuccessfully() {
            var usersList = List.of(user);
            doReturn(usersList)
                    .when(userRepository)
                    .findAll();

            var response = userService.findAll();

            assertNotNull(response);
            assertEquals(usersList.size(), response.size());
        }
    }

    @Nested
    class deleteById {

        User user = new User(UUID.randomUUID().toString(), "mateus@gmail.com", "12142151dsd"
                , UserRole.USER);

        @Test
        @DisplayName("Must delete user successfully")
        void MustDeleteUserSuccessfully() {
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(user.getId());
            doNothing()
                    .when(userRepository)
                    .deleteById(user.getId());

            userService.deleteById(user.getId());

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(userRepository, times(1))
                    .deleteById(user.getId());
        }

        @Test
        @DisplayName("Should throw exception when not finding the user")
        void ShouldThrowExceptionWhenNotFindingTheUser() {
            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(user.getId());

            assertThrows(UserNotFoundException.class,
                    () -> userService.deleteById(user.getId()));

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(userRepository, times(0))
                    .save(user);
        }
    }
}

