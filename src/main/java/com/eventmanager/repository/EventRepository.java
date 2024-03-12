package com.eventmanager.repository;

import com.eventmanager.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStartingDate(LocalDate date);

    Event findByTitle(String title);

    List<Event> findByLocalization(String localization);
}
