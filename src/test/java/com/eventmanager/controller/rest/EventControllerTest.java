package com.eventmanager.controller.rest;

import com.eventmanager.entity.Event;
import com.eventmanager.entity.dtos.EventDto;
import com.eventmanager.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.print.attribute.standard.Media;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
class EventControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private EventService eventService;
    private Event event;
    private EventDto eventDto;

    @BeforeEach
    void setUp() {
        event = Event.builder()
                .localization("Mikołów")
                .title("Rammstein Mikołów station AKS")
                .description("Jak co roku zespół Rammstein zagości na stadionie piłkarskim AKS Mikołów ale dać wspaniałe show. Będą liczne atrakcje dla dzieci, grill i budki z piwem.")
                .startingDate(LocalDate.of(2024, 6, 12))
                .endingDate(LocalDate.of(2024, 7, 12))
                .build();

        eventDto = EventDto.mapToDto(event);
    }

    @DisplayName("testing saving event")
    @Test
    void shouldReturnSavedEventDto() throws Exception {
//        given
        given(eventService.save(eventDto)).willReturn(event);
//        when
        ResultActions response = mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(event))
        );
//        then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(event.getId()))
                .andExpect(jsonPath("$.localization", is(event.getLocalization())))
                .andExpect(jsonPath("$.title", is(event.getTitle())))
                .andExpect(jsonPath("$.startingDate", is(String.valueOf(event.getStartingDate()))))
                .andExpect(jsonPath("$.endingDate", is(String.valueOf(event.getEndingDate()))))
                .andExpect(jsonPath("$.description", is(event.getDescription())))
                .andExpect(jsonPath("$.organizerId").value(event.getOrganizerId()));

    }

    @DisplayName("testing finding all events")
    @Test
    void shouldReturnAllEvents() throws Exception {
//        given
        given(eventService.findAll()).willReturn(List.of(eventDto));
//        when
        ResultActions response = mockMvc.perform(get("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventDto))
        );
//        then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)));
    }
    @DisplayName("testing deleting event by id")
    @Test
    void shouldDeleteEventById() throws Exception {
//        given
        willDoNothing().given(eventService).deleteById(anyLong());
//        when
        ResultActions response = mockMvc.perform(delete("/api/events/{id}", event.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(event))
        );
//        then
        response.andDo(print())
                .andExpect(status().isOk());
    }
    @DisplayName("testing finding event by id")
    @Test
    void shouldReturnEventDtoById() throws Exception {
//        given
        given(eventService.findById(anyLong())).willReturn(Optional.of(eventDto));
//        when
        ResultActions response = mockMvc.perform(get("/api/events/{id}", eventDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(event))
        );
//        then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(eventDto.getId()))
                .andExpect(jsonPath("$.localization", is(eventDto.getLocalization())))
                .andExpect(jsonPath("$.title", is(eventDto.getTitle())))
                .andExpect(jsonPath("$.startingDate", is(String.valueOf(eventDto.getStartingDate()))))
                .andExpect(jsonPath("$.endingDate", is(String.valueOf(eventDto.getEndingDate()))))
                .andExpect(jsonPath("$.description", is(eventDto.getDescription())))
                .andExpect(jsonPath("$.organizerId").value(eventDto.getOrganizerId()));
    }
    @DisplayName("testing finding event by title")
    @Test
    void shouldReturnEventDtoByTitle() throws Exception {
//        given
        given(eventService.findByTitle(anyString())).willReturn(eventDto);
//        when
        ResultActions response = mockMvc.perform(get("/api/events/title/{title}", eventDto.getTitle())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventDto))
        );
//        then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(eventDto.getId()))
                .andExpect(jsonPath("$.localization", is(eventDto.getLocalization())))
                .andExpect(jsonPath("$.title", is(eventDto.getTitle())))
                .andExpect(jsonPath("$.startingDate", is(String.valueOf(eventDto.getStartingDate()))))
                .andExpect(jsonPath("$.endingDate", is(String.valueOf(eventDto.getEndingDate()))))
                .andExpect(jsonPath("$.description", is(eventDto.getDescription())))
                .andExpect(jsonPath("$.organizerId").value(eventDto.getOrganizerId()));
    }
    @DisplayName("testing finding event by localization")
    @Test
    void shouldReturnEventByLocalization() throws Exception {
//        given
        given(eventService.findByLocalization(anyString())).willReturn(List.of(eventDto));
//        when
        ResultActions response = mockMvc.perform(get("/api/events/localization/{localization}", eventDto.getLocalization())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventDto))
        );
//        then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].title", is(eventDto.getTitle())));
    }
}

























