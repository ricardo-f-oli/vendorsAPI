package com.example.vendorsAPI.adapter.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/v1")
public class SystemHealthCheckController {
    public SystemHealthCheckController(){}

    @GetMapping("/public/healthcheck/live")
    public void systemHealthCheckCall(){

    }
}