package com.microservice2.controller;

import com.microservice2.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Microservice2Controller {
    @Autowired
    private Client client;
//    @GetMapping("/ms2")
//    public String callMicroservice1(){
//        return client.getData();
//    }
    @GetMapping("/ms2")
    public String getInformation(){
        return "I am from ms2";
    }
}
