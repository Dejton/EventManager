package com.eventmanager.controller.rest;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.eventmanager.entity.User;
import com.eventmanager.entity.dtos.UserDto;
import com.eventmanager.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private UserService userService;
    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .login("Dejton94")
                .password("password")
                .displayName("Dejton")
                .role("user")
                .build();
        userDto = UserDto.mapToDto(user);
    }

    @DisplayName("testing saving user")
    @Test
    void shouldReturnSavedUser() throws Exception {
//        given
        given(userService.save(userDto)).willReturn(user);
//        when
        ResultActions response = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );
//        then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.login", is(user.getLogin())))
                .andExpect(jsonPath("$.password", is(user.getPassword())))
                .andExpect(jsonPath("$.displayName", is(user.getDisplayName())))
                .andExpect(jsonPath("$.role", is(user.getRole())));

    }
    @DisplayName("testing finding all users")
    @Test
    void shouldReturnAllUsers() throws Exception {
//        given
        given(userService.findAll()).willReturn(List.of(userDto));
//        when
        ResultActions response = mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );
//        then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].id").value(userDto.getId()));
    }
    @DisplayName("testing deleting user by id")
    @Test
    void shouldDeleteUserById() throws Exception {
//        given
        willDoNothing().given(userService).deleteById(anyLong());
//        when
        ResultActions response = mockMvc.perform(delete("/api/users/{id}", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );
//        then
        response.andDo(print())
                .andExpect(status().isOk());
    }
    @DisplayName("testing finding user by id")
    @Test
    void shouldReturnUserById() throws Exception{
//        given
        given(userService.findById(anyLong())).willReturn(Optional.of(userDto));
//        when
        ResultActions response = mockMvc.perform(get("/api/users/{id}", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );
//        then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.login", is(user.getLogin())))
                .andExpect(jsonPath("$.password", is(user.getPassword())))
                .andExpect(jsonPath("$.displayName", is(user.getDisplayName())))
                .andExpect(jsonPath("$.role", is(user.getRole())));
    }
    @DisplayName("testing finding user by login")
    @Test
    void shouldReturnUserByLogin() throws Exception {
//        given
        given(userService.findByLogin(anyString())).willReturn(userDto);
//        when
        ResultActions response = mockMvc.perform(get("/api/users/login/{login}", user.getLogin())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );
//        then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.login", is(user.getLogin())))
                .andExpect(jsonPath("$.password", is(user.getPassword())))
                .andExpect(jsonPath("$.displayName", is(user.getDisplayName())))
                .andExpect(jsonPath("$.role", is(user.getRole())));
    }
    @DisplayName("testing finding users by display name")
    @Test
    void shouldReturnListOfUserDtoByDisplayName() throws Exception{
//        given
        given(userService.findByDisplayName(anyString())).willReturn(List.of(userDto));
//        when
        ResultActions response = mockMvc.perform(get("/api/users/displayname/{displayName}", user.getDisplayName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );
//        then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].id").value(user.getId()));
    }
    @DisplayName("testing finding users by role")
    @Test
    void shouldReturnListOfUSerDtoByRole() throws Exception {
//        given
        given(userService.findByRole(anyString())).willReturn(List.of(userDto));
//        when
        ResultActions response = mockMvc.perform(get("/api/users/role/{role}", user.getRole())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );
//        then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].id").value(user.getId()));
    }
}




































