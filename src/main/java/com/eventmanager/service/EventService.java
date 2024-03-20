package com.eventmanager.service;

import com.eventmanager.entity.Event;
import com.eventmanager.entity.dtos.EventDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventService {
    Event save(EventDto event);
    void deleteById(long id);
    Optional<EventDto> findById(long id);
    List<EventDto> findAll();
    List<EventDto> findFirstTwentyActualEvents();
    List<EventDto> findByStartingDate(LocalDate date);
    EventDto findByTitle(String title);
    List<EventDto> findByStartingDateString(String date);

    List<EventDto> findByLocalization(String localization);
}
