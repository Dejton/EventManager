package com.eventmanager.entity.dtos;

import com.eventmanager.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentDto {
    private long id;
    private String content;
    private LocalDate dateAdded;
    private long userId;
    private long eventId;
    public static CommentDto mapToDto(Comment comment) {
        return  CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .dateAdded(comment.getDateAdded())
                .userId(comment.getUserId())
                .eventId(comment.getEventId())
                .build();
    }
    public static Comment mapFromDto(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .content((commentDto.getContent()))
                .dateAdded((commentDto.getDateAdded()))
                .userId(commentDto.getUserId())
                .eventId(commentDto.getEventId())
                .build();
    }
}



