package com.microservice2.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "MICROSERVICE1")
public interface Client {
    @GetMapping("/message")
    public String getData();
}
