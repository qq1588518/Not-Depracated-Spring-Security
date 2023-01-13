package com.example.withauthmanager.controllers;

import com.example.withauthmanager.models.UserRequest;
import com.example.withauthmanager.models.UserResponse;
import com.example.withauthmanager.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public UserRequest register(@RequestBody UserRequest user) {
        return userService.register(user);
    }

    @GetMapping("/login")
    public String login(@RequestBody UserRequest user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    @GetMapping("/info")
    public UserResponse getRole(@RequestBody UserRequest user) {
        return userService.getUserRole(user.getEmail());
    }
}
