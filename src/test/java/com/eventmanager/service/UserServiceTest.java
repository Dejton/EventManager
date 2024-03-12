package com.eventmanager.service;

import com.eventmanager.entity.User;
import com.eventmanager.repository.UserRepository;
import com.eventmanager.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@DataJpaTest
class UserServiceTest {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserServiceImpl userService = new UserServiceImpl(userRepository);
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .login("Dejton94")
                .password("password")
                .displayName("Dejton")
                .role("user")
                .build();
    }
    @DisplayName("testing saving user")
    @Test
    void shouldReturnSavedUser() {
//        given
        when(userRepository.save(any(User.class))).thenReturn(user);
//        when
        User savedUser = userService.save(user);
//        then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser).isEqualTo(user);
        verify(userRepository).save(user);
    }
    @DisplayName("testing deleting user")
    @Test
    void shouldDeleteUser() {
//        given
        when(userRepository.save(any(User.class))).thenReturn(user);
//        when
        userService.deleteById(user.getId());
//        then
        verify(userRepository).deleteById(user.getId());
    }
    @DisplayName("testing updating user")
    @Test
    void shouldReturnUpdatedUser() {
//        given
        when(userRepository.save(any(User.class))).thenReturn(user);
        user.setPassword("1234");
        user.setDisplayName("Dejton666");
//        when
        User savedUser = userService.save(user);
//        then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getDisplayName()).isEqualTo("Dejton666");
        assertThat(savedUser.getPassword()).isEqualTo("1234");
    }
    @DisplayName("testing finding all users")
    @Test
    void shouldReturnListOfAllUsers() {
//        given
        when(userRepository.findAll()).thenReturn(List.of(user));
//        when
        List<User> users = userService.findAll();
//        then
        assertThat(users).isNotEmpty();
        assertThat(users.size()).isEqualTo(1);
    }
    @DisplayName("testing finding user by id")
    @Test
    void shouldReturnUserById() {
//        given
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
//        when
        User foundUser = userService.findById(user.getId()).get();
//        then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(user);
    }
    @DisplayName("testing finding user by login")
    @Test
    void shouldReturnUserByLogin() {
//        given
        when(userRepository.findByLogin(anyString())).thenReturn(user);
//        when
        User foundUser = userService.findByLogin("Dejton94");
//        then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(user);
    }
    @DisplayName("testing finding users by role")
    @Test
    void shouldReturnListOfUsersByRole() {
//        given
        when(userRepository.findByRole(anyString())).thenReturn(List.of(user));
//        when
        List<User> users = userService.findByRole("user");
//        then
        assertThat(users).isNotEmpty();
        assertThat(users.size()).isEqualTo(1);
    }
    @DisplayName("testing finding users by Display name")
    @Test
    void shouldReturnListOfUsersByDisplayName() {
//        given
        when(userRepository.findByDisplayName(anyString())).thenReturn(List.of(user));
//        when
        List<User> users = userService.findByDisplayName("Dejton");
//        then
        assertThat(users).isNotEmpty();
        assertThat(users.size()).isEqualTo(1);
    }
}




























