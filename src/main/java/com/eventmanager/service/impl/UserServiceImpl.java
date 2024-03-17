package com.eventmanager.service.impl;

import com.eventmanager.entity.User;
import com.eventmanager.entity.dtos.UserDto;
import com.eventmanager.repository.UserRepository;
import com.eventmanager.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDto> findById(long id) {
        return Optional.of(UserDto.mapToDto(userRepository.findById(id).get()));
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDto::mapToDto)
                .toList();
    }

    @Override
    public User save(UserDto userDto) {
        User user = UserDto.mapFromDto(userDto);
        return userRepository.save(user);
    }

    @Override
    public UserDto findByLogin(String login) {
        return UserDto.mapToDto(userRepository.findByLogin(login));
    }

    @Override
    public List<UserDto> findByDisplayName(String displayName) {
        List<User> users = userRepository.findByDisplayName(displayName);
        return users.stream()
                .map(UserDto::mapToDto)
                .toList();
    }

    @Override
    public List<UserDto> findByRole(String role) {
        List<User> users = userRepository.findByRole(role);
        return users.stream()
                .map(UserDto::mapToDto)
                .toList();
    }
}
