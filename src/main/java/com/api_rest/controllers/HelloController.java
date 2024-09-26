package com.api_rest.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class HelloController {
    @GetMapping("/")
    public String hello(){
        return "index";
    }
}
