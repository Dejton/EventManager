package com.eventmanager.controller.rest;

import com.eventmanager.entity.Comment;
import com.eventmanager.entity.dtos.CommentDto;
import com.eventmanager.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
