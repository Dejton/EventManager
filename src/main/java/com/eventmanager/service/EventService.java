package com.eventmanager.service;

import com.eventmanager.entity.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventService {
    Event save(Event event);
    void deleteById(long id);
    Optional<Event> findById(long id);
    List<Event> findAll();
    List<Event> findByStartingDate(LocalDate date);

    Event findByTitle(String title);

    List<Event> findByLocalization(String localization);
}
