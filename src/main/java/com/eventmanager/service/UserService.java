package com.eventmanager.service;

import com.eventmanager.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void deleteById(long id);
    Optional<User> findById(long id);
    List<User> findAll();
    User save(User user);
    User findByLogin(String login);

    List<User> findByDisplayName(String displayName);

    List<User> findByRole(String user);
}
