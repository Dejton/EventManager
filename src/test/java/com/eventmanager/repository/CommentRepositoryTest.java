package com.eventmanager.repository;

import com.eventmanager.entity.Comment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;
    private Comment comment;

    @BeforeEach
    void setUp() {

         comment = Comment.builder()
                .content("Super wydarzenie, już nie mogę się doczekać!!!")
                .dateAdded(LocalDate.of(2024, 5, 5))
                .userId(1)
                .eventId(2)
                .build();
    }
    @DisplayName("testing saving comment")
    @Test
    void shouldSaveComment() {
//        given
//        when
        Comment savedComment = commentRepository.save(comment);
        System.out.println(comment);
//        then
        assertThat(savedComment).isNotNull();
        assertThat(savedComment).isEqualTo(comment);
    }
    @DisplayName("testing deleting comment by id")
    @Test
    void shouldDeleteCommentById() {
//        given
        Comment savedComment = commentRepository.save(comment);
//        when
        commentRepository.deleteById(comment.getId());
        Optional<Comment> foundComment = commentRepository.findById(comment.getId());
//        then
        assertThat(foundComment).isEmpty();
    }
    @DisplayName("testing updating comment")
    @Test
    void shouldReturnUpdatedComment() {
//        given
        Comment savedComment = commentRepository.save(comment);
        savedComment.setContent("Ale bieda koncert xDD");
//        when
        Comment updatedComment = commentRepository.save(savedComment);
//        then
        assertThat(updatedComment).isNotNull();
        assertThat(updatedComment).isEqualTo(savedComment);
    }
    @DisplayName("testing finding all comments")
    @Test
    void shouldReturnListOfAllComments() {
//        given
        Comment comment2 = Comment.builder()
                .content("Super wydarzenie, już nie mogę się doczekać!!!")
                .dateAdded(LocalDate.of(2024, 5, 5))
                .userId(1)
                .eventId(2)
                .build();
        commentRepository.save(comment);
        commentRepository.save(comment2);
//        when
        List<Comment> comments = commentRepository.findAll();
//        then
        assertThat(comments).isNotEmpty();
        assertThat(comments.size()).isEqualTo(2);
    }
    @DisplayName("testing finding comments by id")
    @Test
    void shouldReturnCommentById() {
//        given
        commentRepository.save(comment);
//        when
        Optional<Comment> foundComment = commentRepository.findById(comment.getId());
//        then
        assertThat(foundComment).isNotNull();
        assertThat(foundComment.get()).isEqualTo(comment);
    }
    @DisplayName("testing finding comments by Date")
    @Test
    void shouldReturnCommentByDate() {
//        given
        Comment savedComment = commentRepository.save(comment);
//        when
        List<Comment> comments = commentRepository.findByDateAdded(LocalDate.of(2024,5,5));
//        then
        assertThat(comments).isNotEmpty();
        assertThat(comments.size()).isEqualTo(1);
    }
    @DisplayName("testing finding comments by user")
    @Test
    void shouldReturnListOfCommentsByUser() {
//        given
        Comment savedComment = commentRepository.save(comment);
//        when
        List<Comment> comments = commentRepository.findByUserId(1);
//        then
        assertThat(comments).isNotEmpty();
        assertThat(comments.size()).isEqualTo(1);
    }
}






























