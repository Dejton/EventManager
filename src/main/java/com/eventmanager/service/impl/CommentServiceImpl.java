package com.eventmanager.service.impl;

import com.eventmanager.entity.Comment;
import com.eventmanager.entity.dtos.CommentDto;
import com.eventmanager.repository.CommentRepository;
import com.eventmanager.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Comment save(CommentDto commentDto) {
        // map to DAO
        Comment comment = CommentDto.mapFromDto(commentDto);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentDto> findAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(CommentDto::mapToDto)
                .toList();
    }
    @Override
    public Optional<CommentDto> findById(long id) {
        return Optional.of(CommentDto.mapToDto(commentRepository.findById(id).get()));
    }

    @Override
    public List<CommentDto> findByDateAdded(LocalDate dateAdded) {
        List<Comment> comments = commentRepository.findByDateAdded(dateAdded);
        return comments.stream()
                .map(CommentDto::mapToDto)
                .toList();
    }

    @Override
    public List<CommentDto> findByUserId(long userId) {
        List<Comment> comments = commentRepository.findByUserId(userId);
        return comments.stream()
                .map(CommentDto::mapToDto)
                .toList();
    }
}
