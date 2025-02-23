package com.example.greetingapp.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    // Method to return simple greeting
    public String getGreetingMessage() {
        return "Hello World from Service Layer!";
    }
}
