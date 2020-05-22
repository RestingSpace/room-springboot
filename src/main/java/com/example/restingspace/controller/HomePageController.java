package com.example.restingspace.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Writer;

@RestController
public class HomePageController {


    @RequestMapping(path="/login", method = RequestMethod.POST)
    public void login(@RequestParam String username,
                      @RequestParam String password,
                      @RequestParam boolean auth,
                      Writer writer) throws IOException {

    }
}
