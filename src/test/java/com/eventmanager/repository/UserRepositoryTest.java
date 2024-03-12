package com.eventmanager.repository;

import com.eventmanager.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    User user;

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
    void shouldSaveUser() {
//        given
//        when
        User savedUser = userRepository.save(user);
//        then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser).isEqualTo(user);
    }

    @DisplayName("testing deleting user by id")
    @Test
    void shouldDeleteUserById() {
//        given
        User savedUser = userRepository.save(user);
//        when
        userRepository.deleteById(user.getId());
        Optional<User> foundUser = userRepository.findById(user.getId());
//        then
        assertThat(foundUser).isEmpty();
    }

    @DisplayName("testing updating user")
    @Test
    void shouldReturnUpdatedUser() {
//        given
        User savedUser = userRepository.save(user);
        savedUser.setLogin("Dejton93");
        savedUser.setPassword("1234");
//        when
        User updatedUser = userRepository.save(savedUser);
//        then
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser).isEqualTo(savedUser);
    }

    @DisplayName("testing finding all users")
    @Test
    void shouldReturnAllUsers() {
//        given
        User user2 = User.builder()
                .login("Dejton666")
                .password("password")
                .displayName("Dejton")
                .role("user")
                .build();
        userRepository.save(user);
        userRepository.save(user2);
//        when
        List<User> users = userRepository.findAll();
//        then
        assertThat(users).isNotEmpty();
        assertThat(users.size()).isEqualTo(2);
    }

    @DisplayName("testing finding user by id")
    @Test
    void shouldReturnUserById() {
//        given
        User savedUser = userRepository.save(user);
//        when
        Optional<User> foundUser = userRepository.findById(user.getId());
//        then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.get()).isEqualTo(savedUser);
    }

    @DisplayName("testing finding user by login")
    @Test
    void shouldReturnUserByLogin() {
//        given
        User savedUser = userRepository.save(user);
//        when
        User foundUser = userRepository.findByLogin("Dejton94");
//        then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(savedUser);
    }
    @DisplayName("testing finding user by displayName")
    @Test
    void shouldReturnListOfUsersByDisplayName() {
//        given
        User user2 = User.builder()
                .login("Dejton666")
                .password("password")
                .displayName("Dejton")
                .role("user")
                .build();
        userRepository.save(user);
        userRepository.save(user2);
//        when
        List<User> users = userRepository.findByDisplayName("Dejton");
//        then
        assertThat(users).isNotEmpty();
        assertThat(users.size()).isEqualTo(2);
    }
    @DisplayName("testing finding users by role")
    @Test
    void shouldReturnListOfUsersByRole() {
//        given
        User user2 = User.builder()
                .login("Dejton666")
                .password("password")
                .displayName("Dejton")
                .role("user")
                .build();
        userRepository.save(user);
        userRepository.save(user2);
//        when
        List<User> users = userRepository.findByRole("user");
//        then
        assertThat(users).isNotEmpty();
        assertThat(users.get(0)).isEqualTo(user);
        assertThat(users.size()).isEqualTo(2);
    }
}


























