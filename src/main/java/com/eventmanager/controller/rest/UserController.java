package com.eventmanager.controller.rest;

import com.eventmanager.entity.User;
import com.eventmanager.entity.dtos.UserDto;
import com.eventmanager.service.UserService;
import com.eventmanager.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }
    @GetMapping
    public List<UserDto> getAll() {
        return userService.findAll();
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        userService.deleteById(id);
    }
    @GetMapping("/{id}")
    public Optional<UserDto> getById(@PathVariable long id) {
        return userService.findById(id);
    }
    @GetMapping("/login/{login}")
    public UserDto getByLogin(@PathVariable String login) {
        return userService.findByLogin(login);
    }
    @GetMapping("/displayname/{displayName}")
    public List<UserDto> getByDisplayName(@PathVariable String displayName) {
        return userService.findByDisplayName(displayName);
    }
    @GetMapping("/role/{role}")
    public List<UserDto> getByRole(@PathVariable String role) {
        return userService.findByRole(role);
    }
}
