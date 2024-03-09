package com.api.book.bootrestbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Sample {
    
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    
}
