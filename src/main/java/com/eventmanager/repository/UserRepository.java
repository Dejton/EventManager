package com.eventmanager.repository;

import com.eventmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    List<User> findByDisplayName(String displayName);

    List<User> findByRole(String user);
}
