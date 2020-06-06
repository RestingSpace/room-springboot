package com.example.restingspace.controller;

import com.example.restingspace.model.Reservation;
import com.example.restingspace.model.User;
import com.example.restingspace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    public void registration(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.addUser(user);
    }

    @GetMapping("/getUserByUsername/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

//    @GetMapping("/getReservationsByUsername/{username}")
//    public List<Reservation> getReservationsByUsername(@PathVariable String username){
//        return userService.getReservationsByUsername(username);
//    }
}
