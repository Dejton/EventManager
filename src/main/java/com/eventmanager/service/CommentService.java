package com.eventmanager.service;

import com.eventmanager.entity.Comment;
import com.eventmanager.entity.dtos.CommentDto;
import com.eventmanager.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CommentService {
   Comment save(CommentDto comment);
   void deleteById(long id);
   List<CommentDto> findAll();
   Optional<CommentDto> findById(long id);
    List<CommentDto> findByDateAdded(LocalDate dateAdded);
    List<CommentDto> findByDateAddedString(String date);
    List<CommentDto> findByUserId(long userId);
}
