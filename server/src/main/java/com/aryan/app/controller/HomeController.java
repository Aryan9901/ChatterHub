package com.aryan.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home() {
        return "this is home controller";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "this is dashboard controller";
    }
}
