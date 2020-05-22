package com.example.restingspace.controller;

import com.example.restingspace.model.User;
import com.example.restingspace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public void registration(@RequestBody User user) {
        userService.addUser(user);
    }

    @PostMapping("/getUser")
    public User registration(@RequestBody String username) {
        return userService.getUserByUsername(username);
    }

}
