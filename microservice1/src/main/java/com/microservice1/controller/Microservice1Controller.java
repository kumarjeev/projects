package com.microservice1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Microservice1Controller {
    @GetMapping("/message")
    public String getData(){
        return "i am from microservice1";
    }
}
