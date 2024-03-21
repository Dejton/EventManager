package com.eventmanager.controller.web;

import com.eventmanager.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainPageController {
    private final EventService eventService;

    public MainPageController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping
    public String getEvents(Model model) {
        model.addAttribute("events", eventService.findFirstTwentyActualEvents());
        return "main-page";
    }
}
