package com.eventmanager.service;

import com.eventmanager.entity.Comment;
import com.eventmanager.entity.dtos.CommentDto;
import com.eventmanager.repository.CommentRepository;
import com.eventmanager.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.configuration.IMockitoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@RequiredArgsConstructor
class CommentServiceTest {
    private final CommentRepository commentRepository = mock(CommentRepository.class);
    private final CommentServiceImpl commentService = new CommentServiceImpl(commentRepository);
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

    @DisplayName("testing save comment")
    @Test
    void shouldReturnSavedComment() {
//        given
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
//        when
        Comment savedComment = commentService.save(commentDto);
//        then
        assertThat(savedComment).isNotNull();
        assertThat(savedComment).isEqualTo(comment);
        verify(commentRepository).save(comment);
    }
    @DisplayName("testing delete comment by Id")
    @Test
    void shouldDeleteCommentById() {
//        given
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
//        when
        commentService.deleteById(comment.getId());
//        then
        verify(commentRepository).deleteById(comment.getId());
    }
    @DisplayName("testing finding all comments")
    @Test
    void shouldReturnListOfAllComments() {
//        given
        when(commentRepository.findAll()).thenReturn(List.of(comment));
//        when
        List<CommentDto> comments = commentService.findAll();
//        then
        assertThat(comments).isNotEmpty();
        assertThat(comments.size()).isEqualTo(1);
    }
    @DisplayName("testing updating comment")
    @Test
    void shouldReturnUpdatedComment() {
//        given
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
        comment.setContent("Wydarzenie odwołane");
//        when
        Comment updatedComment = commentService.save(commentDto);
//        then
        assertThat(updatedComment).isNotNull();
        assertThat(updatedComment.getContent()).isEqualTo("Wydarzenie odwołane");
    }
    @DisplayName("testing finding comment by id")
    @Test
    void shouldReturnCommentById() {
//        given
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(comment));
//        when
        CommentDto foundComment = commentService.findById(comment.getId()).get();
//        then
        assertThat(foundComment).isNotNull();
        assertThat(foundComment).isEqualTo(commentDto);
    }
    @DisplayName("testing finding comment by date")
    @Test
    void shouldReturnListOfCommentsByDate() {
//        given
        when(commentRepository.findByDateAdded(LocalDate.of(2024, 5, 5))).thenReturn(List.of(comment));
//        when
        List<CommentDto> comments = commentService.findByDateAdded(LocalDate.of(2024, 5, 5));
//        then
        assertThat(comments).isNotEmpty();
        assertThat(comments.size()).isEqualTo(1);
    }
    @DisplayName("testing finding comments by dateSAddedString")
    @Test
    void shouldReturnListOfCommentDtoByDateAddedString() {
//        given
        when(commentRepository.findByDateAdded(LocalDate.of(2024, 5, 5))).thenReturn(List.of(comment));
//        when
        List<CommentDto> comments = commentService.findByDateAddedString("2024-05-05");
//        then
        assertThat(comments.size()).isEqualTo(1);
        assertThat(comments.get(0)).isEqualTo(commentDto);
    }
    @DisplayName("testing finding comments by dateAddedString with wrong date")
    @Test
    void shouldThrowExceptionWithWornDateFormat() {
//        given
        when(commentRepository.findByDateAdded(LocalDate.of(2024, 5, 5))).thenReturn(List.of(comment));
//        when
//        then
//        assertThatThrownBy()
        assertThrows(DateTimeParseException.class, () -> {
            commentService.findByDateAddedString("2024,05,05");
        });
    }
    @DisplayName("testing finding comments by user")
    @Test
    void shouldReturnListOfCommentsByUser() {
//        given
        when(commentRepository.findByUserId(anyLong())).thenReturn(List.of(comment));
//        when
        List<CommentDto> comments = commentService.findByUserId(comment.getUserId());
//        then
        assertThat(comments).isNotEmpty();
        assertThat(comments.size()).isEqualTo(1);
    }
}






















