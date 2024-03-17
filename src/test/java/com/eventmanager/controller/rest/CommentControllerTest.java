package com.eventmanager.controller.rest;

import com.eventmanager.entity.Comment;
import com.eventmanager.entity.dtos.CommentDto;
import com.eventmanager.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired MockMvc mockMvc;
    @MockBean
    private CommentService commentService;
    private Comment comment;
    private CommentDto commentDto;

    @BeforeEach
    void setUp() {
        comment = Comment.builder()
                .content("Super wydarzenie, już nie mogę się doczekać!!!")
                .dateAdded(LocalDate.of(2024, 5, 5))
                .userId(1)
                .eventId(2)
                .build();
        commentDto = CommentDto.mapToDto(comment);
    }
    @DisplayName("testing adding new comment")
    @Test
    void shouldReturnSavedComment() throws Exception {
//        given
        given(commentService.save(any(CommentDto.class))).willReturn(comment);
//        when
        ResultActions response = mockMvc.perform(post("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comment))
        );
//        then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content", is(comment.getContent())));
    }
    @DisplayName("testing finding all comments")
    @Test
    void shouldReturnListOfCommentsDto() throws Exception {
//        given
        given(commentService.findAll()).willReturn(List.of(commentDto));
//        when
        ResultActions response = mockMvc.perform(get("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentDto))
        );
//        then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)));
    }
}







































