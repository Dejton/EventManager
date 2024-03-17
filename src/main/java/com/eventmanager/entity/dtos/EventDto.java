package com.eventmanager.entity.dtos;

import com.eventmanager.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
    private long id;
    private String localization;
    private String title;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private String description;
    private long organizerId;

    public static EventDto mapToDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .localization(event.getLocalization())
                .title(event.getTitle())
                .startingDate(event.getStartingDate())
                .endingDate(event.getEndingDate())
                .description(event.getDescription())
                .organizerId(event.getOrganizerId())
                .build();
    }
    public static Event mapFromDto(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .localization(eventDto.getLocalization())
                .title(eventDto.getTitle())
                .startingDate(eventDto.getStartingDate())
                .endingDate(eventDto.getEndingDate())
                .description(eventDto.getDescription())
                .organizerId(eventDto.getOrganizerId())
                .build();
    }
}


