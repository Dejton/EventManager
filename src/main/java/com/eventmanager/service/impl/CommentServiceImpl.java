package com.eventmanager.service.impl;

import com.eventmanager.entity.Comment;
import com.eventmanager.repository.CommentRepository;
import com.eventmanager.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findByDateAdded(LocalDate dateAdded) {
        return commentRepository.findByDateAdded(dateAdded);
    }

    @Override
    public List<Comment> findByUserId(long userId) {
        return commentRepository.findByUserId(userId);
    }
}
