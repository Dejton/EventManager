package com.eventmanager.service.impl;

import com.eventmanager.entity.Event;
import com.eventmanager.repository.EventRepository;
import com.eventmanager.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteById(long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> findById(long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findByStartingDate(LocalDate date) {
        return eventRepository.findByStartingDate(date);
    }

    @Override
    public Event findByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

    @Override
    public List<Event> findByLocalization(String localization) {
        return eventRepository.findByLocalization(localization);
    }


}
