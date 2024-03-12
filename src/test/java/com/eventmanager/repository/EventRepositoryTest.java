package com.eventmanager.repository;

import com.eventmanager.entity.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@DataJpaTest
class EventRepositoryTest {
    @Autowired
    private EventRepository eventRepository;

    Event event;

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

    @DisplayName("testing saving new event, happy path")
    @Test
    void shouldSaveEvent() {
//        given
//        when
        Event savedEvent = eventRepository.save(event);
//        then
        assertThat(savedEvent).isNotNull();
        assertThat(savedEvent).isEqualTo(event);
        assertThat(savedEvent.getId()).isGreaterThan(0);
    }

    @DisplayName("testing saving new event, with the same title")
    @Test
    void shouldNotSaveEventWithTheSameTitle() {
//        given
        Event event2 = Event.builder()
                .localization("Mikołów")
                .title("Rammstein Mikołów station AKS")
                .description("Jak co roku zespół Rammstein zagości na stadionie piłkarskim AKS Mikołów ale dać wspaniałe show. Będą liczne atrakcje dla dzieci, grill i budki z piwem.")
                .startingDate(LocalDate.of(2024, 6, 12))
                .endingDate(LocalDate.of(2024, 7, 12))
                .build();
        eventRepository.save(event);
//        when
//        then
        assertThrows(DataIntegrityViolationException.class, () -> {
            eventRepository.save(event2);
        });
    }

    @DisplayName("testing delete event, happy path")
    @Test
    void shouldDeleteEvent() {
//        given
        eventRepository.save(event);
//        when
        eventRepository.deleteById(event.getId());
        Optional<Event> foundEvent = eventRepository.findById(event.getId());
//        then
        assertThat(foundEvent).isEmpty();
    }

    @DisplayName("testing updating event")
    @Test
    void shouldUpdateEvent() {
//        given
        eventRepository.save(event);
        Event savedEvent = eventRepository.findById(event.getId()).get();
        savedEvent.setDescription("Wydarzenie odwołane");
        savedEvent.setTitle("Rammstein Mikołów station AKS ODWOŁANE");
//        when
        Event updatedEvent = eventRepository.save(savedEvent);
//        then
        assertThat(updatedEvent.getTitle()).isEqualTo(savedEvent.getTitle());
        assertThat(updatedEvent.getDescription()).isEqualTo(savedEvent.getDescription());
    }

    @DisplayName("testing get events list")
    @Test
    void shouldReturnEventsList() {
//        given
        Event event2 = Event.builder()
                .localization("Mikołów")
                .title("Slayer Mikołów station AKS")
                .description("Jak co roku zespół Rammstein zagości na stadionie piłkarskim AKS Mikołów ale dać wspaniałe show. Będą liczne atrakcje dla dzieci, grill i budki z piwem.")
                .startingDate(LocalDate.of(2024, 6, 12))
                .endingDate(LocalDate.of(2024, 7, 12))
                .build();
        eventRepository.save(event);
        eventRepository.save(event2);
//        when
        List<Event> events = eventRepository.findAll();
//        then
        assertThat(events).isNotEmpty();
        assertThat(events.size()).isEqualTo(2);
    }
    @DisplayName("testing finding events by date")
    @Test
    void shouldReturnListOfEventsByDate() {
//        given
        eventRepository.save(event);
//        when
       List<Event> events = eventRepository.findByStartingDate(LocalDate.of(2024,6,12));
//        then
        assertThat(events).isNotEmpty();
        assertThat(events.size()).isEqualTo(1);
    }
    @DisplayName("testing finding event by title")
    @Test
    void shouldReturnEventByTitle() {
//        given
        eventRepository.save(event);
//        when
        Event foundEvent = eventRepository.findByTitle("Rammstein Mikołów station AKS");
//        then
        assertThat(foundEvent).isNotNull();
        assertThat(foundEvent).isEqualTo(event);
    }

    @DisplayName("testing finding events by localization")
    @Test
    void shouldReturnListOfEventsByLocalization() {
//        given
        Event event2 = Event.builder()
                .localization("Mikołów")
                .title("Slayer Mikołów station AKS")
                .description("Jak co roku zespół Rammstein zagości na stadionie piłkarskim AKS Mikołów ale dać wspaniałe show. Będą liczne atrakcje dla dzieci, grill i budki z piwem.")
                .startingDate(LocalDate.of(2024, 6, 12))
                .endingDate(LocalDate.of(2024, 7, 12))
                .build();
        eventRepository.save(event);
        eventRepository.save(event2);
//        when
        List<Event> events = eventRepository.findByLocalization("Mikołów");
//        then
        assertThat(events).isNotEmpty();
        assertThat(events.size()).isEqualTo(2);
    }
}

































