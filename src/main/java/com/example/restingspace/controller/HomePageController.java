package com.example.restingspace.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {
    @GetMapping("/")
    public String sayIndex(){
        return "index";
    }

//    @RequestMapping("/login")
//    public String login(@RequestParam(value="error", required =false) String error,
//                        @RequestParam(value="logout",required = false) String logout, Model model){
//        if(error!=null){
//            model.addAttribute("error", "InValid username and password");
//        }
//        if(logout!=null){
//            model.addAttribute("logout", "You have logged out successfully");
//        }
//        return "login";
//    }
}
