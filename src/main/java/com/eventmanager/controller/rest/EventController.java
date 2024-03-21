package com.eventmanager.controller.rest;

import com.eventmanager.entity.Event;
import com.eventmanager.entity.dtos.EventDto;
import com.eventmanager.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event save(@RequestBody EventDto eventDto) {
        return eventService.save(eventDto);
    }
    @GetMapping
    public List<EventDto> getFirst20() {
        return eventService.findFirstTwentyActualEvents();
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        eventService.deleteById(id);
    }
    @GetMapping("/{id}")
    public Optional<EventDto> getById(@PathVariable long id) {
        return eventService.findById(id);
    }
    @GetMapping("/title/{title}")
    public EventDto getByTitle(@PathVariable String title) {
        return eventService.findByTitle(title);
    }
    @GetMapping("/localization/{localization}")
    public List<EventDto> getByLocalization(@PathVariable String localization) {
        return eventService.findByLocalization(localization);
    }
    @GetMapping("/date/{date}")
    public List<EventDto> getByStartingDateString(@PathVariable String date) {
        return eventService.findByStartingDateString(date);
    }
}
