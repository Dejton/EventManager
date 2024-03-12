package com.eventmanager.service;

import com.eventmanager.entity.Event;
import com.eventmanager.repository.EventRepository;
import com.eventmanager.service.impl.EventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
@DataJpaTest
class EventServiceTest {
    private final EventRepository eventRepository = mock(EventRepository.class);
    private final EventServiceImpl eventService = new EventServiceImpl(eventRepository);
    private Event event;


    @BeforeEach
    void setUp() {
        event = Event.builder()
                .localization("Mikołów")
                .title("Rammstein Mikołów station AKS")
                .description("Jak co roku zespół Rammstein zagości na stadionie piłkarskim AKS Mikołów ale dać wspaniałe show. Będą liczne atrakcje dla dzieci, grill i budki z piwem.")
                .startingDate(LocalDate.of(2024, 6, 12))
                .endingDate(LocalDate.of(2024, 7, 12))
                .build();
    }

    @DisplayName("testing saving event")
    @Test
    void shouldReturnSavedEvent() {
//        given
        when(eventRepository.save(any(Event.class))).thenReturn(event);
//        when
        Event savedEvent = eventService.save(event);
//        then
        assertThat(savedEvent).isNotNull();
        assertThat(savedEvent).isEqualTo(event);
        verify(eventRepository).save(event);
    }
    @DisplayName("testing deleting event by id")
    @Test
    void shouldDeleteEventById() {
//        given
        when(eventRepository.save(any(Event.class))).thenReturn(event);
//        when
        eventService.deleteById(event.getId());
//        then
        verify(eventRepository).deleteById(event.getId());
    }
    @DisplayName("testing update event")
    @Test
    void shouldReturnUpdatedEvent() {
//        given
        when(eventRepository.save(event)).thenReturn(event);
        event.setTitle("Slayer Mikołów station AKS");
        event.setLocalization("Kraków");
//        when
        Event updatedEvent = eventService.save(event);
//        then
        assertThat(updatedEvent).isNotNull();
        assertThat(updatedEvent).isEqualTo(event);
    }
    @DisplayName("testing finding all events")
    @Test
    void shouldReturnListOfAllEvents() {
//        given
        Event event2 = event = Event.builder()
                .localization("Mikołów")
                .title("Slayer Mikołów station AKS")
                .description("Jak co roku zespół Rammstein zagości na stadionie piłkarskim AKS Mikołów ale dać wspaniałe show. Będą liczne atrakcje dla dzieci, grill i budki z piwem.")
                .startingDate(LocalDate.of(2024, 6, 12))
                .endingDate(LocalDate.of(2024, 7, 12))
                .build();
        when(eventRepository.findAll()).thenReturn(List.of(event, event2));
//        when
        List<Event> events = eventService.findAll();
//        then
        assertThat(events).isNotEmpty();
        assertThat(events.size()).isEqualTo(2);
    }
    @DisplayName("testing finding event by id")
    @Test
    void shouldReturnEventById() {
//        given
        when(eventRepository.findById(anyLong())).thenReturn(Optional.of(event));
//        when
        Optional<Event> foundEvent = eventService.findById(event.getId());
//        then
        assertThat(foundEvent).isNotNull();
        assertThat(foundEvent.get()).isEqualTo(event);
    }
    @DisplayName("testing finding events by date")
    @Test
    void shouldReturnListOfEventsByDate() {
//        given
        when(eventRepository.findByStartingDate(LocalDate.of(2024,6,12))).thenReturn(List.of(event));
//        when
        List<Event> events = eventService.findByStartingDate(LocalDate.of(2024, 6, 12));
//        then
        assertThat(events).isNotEmpty();
        assertThat(events.size()).isEqualTo(1);
    }
    @DisplayName("testing finding event by title")
    @Test
    void shouldReturnEventByTitle() {
//        given
        when(eventRepository.findByTitle(anyString())).thenReturn(event);
//        when
        Event foundEvent = eventService.findByTitle("Rammstein Mikołów station AKS");
//        then
        assertThat(foundEvent).isNotNull();
        assertThat(foundEvent).isEqualTo(event);
    }
    @DisplayName("testing finding events by localization")
    @Test
    void shouldReturnListOfEventsByLocalization() {
//        given
        when(eventRepository.findByLocalization(anyString())).thenReturn(List.of(event));
//        when
        List<Event> events = eventService.findByLocalization("Mikołów");
//        then
        assertThat(events).isNotEmpty();
        assertThat(events.size()).isEqualTo(1);
    }
}































