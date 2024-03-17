package com.eventmanager.service.impl;

import com.eventmanager.entity.Event;
import com.eventmanager.entity.dtos.EventDto;
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
    public Event save(EventDto eventDto) {
        Event event = EventDto.mapFromDto(eventDto);
        return eventRepository.save(event);
    }

    @Override
    public void deleteById(long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<EventDto> findById(long id) {
        return Optional.of(EventDto.mapToDto(eventRepository.findById(id).get()));
    }

    @Override
    public List<EventDto> findAll() {
        List<Event> events = eventRepository.findAll();
        return  events.stream()
                .map(EventDto::mapToDto)
                .toList();
    }

    @Override
    public List<EventDto> findByStartingDate(LocalDate date) {
        List<Event> events = eventRepository.findByStartingDate(date);
        return events.stream()
                .map(EventDto::mapToDto)
                .toList();
    }

    @Override
    public EventDto findByTitle(String title) {
        return EventDto.mapToDto(eventRepository.findByTitle(title));
    }

    @Override
    public List<EventDto> findByLocalization(String localization) {
        List<Event> events = eventRepository.findByLocalization(localization);
        return events.stream()
                .map(EventDto::mapToDto)
                .toList();
    }


}
