package com.eventmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String content;
    @Column(name = "date_added", nullable = false)
    private LocalDate dateAdded;
    @Column(name = "user_id", nullable = false)
    private long userId;    // klucz obcy do User, jeden do wielu
    @Column(name = "event_id", nullable = false)
    private long eventId;   // klucz obcy do Event, jeden do wielu


}
