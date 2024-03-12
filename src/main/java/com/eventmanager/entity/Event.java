package com.eventmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String localization;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(name = "starting_date", nullable = false)
    private LocalDate startingDate;
    @Column(name = "ending_date", nullable = false)
    private LocalDate endingDate;
    @Column(nullable = false)
    private String description;
    @Column(name = "organizer_id", nullable = false)
    private long organizerId;   //   klucz obcy od User wiele do jednego


}
