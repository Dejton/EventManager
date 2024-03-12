package com.eventmanager.repository;

import com.eventmanager.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByDateAdded(LocalDate dateAdded);

    List<Comment> findByUserId(long userId);
}
