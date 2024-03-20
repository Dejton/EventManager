package com.eventmanager.controller.rest;

import com.eventmanager.entity.Comment;
import com.eventmanager.entity.dtos.CommentDto;
import com.eventmanager.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment saveComment(@RequestBody CommentDto comment) {
        return commentService.save(comment);
    }

    @GetMapping
    public List<CommentDto> getAll() {
        return commentService.findAll();
    }
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable long id) {
        commentService.deleteById(id);
    }
    @GetMapping("/{id}")
    public Optional<CommentDto> getById(@PathVariable long id) {
        return commentService.findById(id);
    }
    @GetMapping("/date/{date}")
    public List<CommentDto> getByDateAddedString(@PathVariable String date) {
        return commentService.findByDateAddedString(date);
    }
    @GetMapping("user/{id}")
    public List<CommentDto> getByUserId(@PathVariable long id) {
        return commentService.findByUserId(id);
    }
}
