package com.eventmanager.service;

import com.eventmanager.entity.User;
import com.eventmanager.entity.dtos.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void deleteById(long id);
    Optional<UserDto> findById(long id);
    List<UserDto> findAll();
    User save(UserDto userDto);
    UserDto findByLogin(String login);

    List<UserDto> findByDisplayName(String displayName);

    List<UserDto> findByRole(String user);
}
