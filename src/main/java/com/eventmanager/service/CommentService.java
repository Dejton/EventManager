package com.eventmanager.service;

import com.eventmanager.entity.Comment;
import com.eventmanager.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CommentService {
   Comment save(Comment comment);
   void deleteById(long id);
   List<Comment> findAll();
   Optional<Comment> findById(long id);
    List<Comment> findByDateAdded(LocalDate dateAdded);

    List<Comment> findByUserId(long userId);
}
