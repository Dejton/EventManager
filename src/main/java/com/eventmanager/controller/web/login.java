package com.eventmanager.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/login")
public class login {

    @GetMapping
    public String showLogin(Model model) {

        return "login-page";
    }
}
