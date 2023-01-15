package com.example.withauthmanager.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/info")
    public String admin(Authentication authentication) {
        return "You are admin!\n" + authentication.getAuthorities() +
                "\nYour email:" + authentication.getPrincipal().toString();
    }
}
