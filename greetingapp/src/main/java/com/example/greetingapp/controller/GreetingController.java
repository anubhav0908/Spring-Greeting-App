package com.example.greetingapp.controller;

import com.example.greetingapp.model.Greeting;
import com.example.greetingapp.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/greet")
public class GreetingController {

    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // GET Method for Personalized Greetings
    @GetMapping
    public Map<String, String> getGreeting(@RequestParam(required = false) String firstName,
                                           @RequestParam(required = false) String lastName) {
        Map<String, String> response = new HashMap<>();
        String greetingMessage = greetingService.getPersonalizedGreeting(firstName, lastName);
        response.put("message", greetingMessage);
        return response;
    }

    // POST Method to Save Greeting
    @PostMapping
    public Greeting postGreeting(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        return greetingService.saveGreeting(message);
    }

    // PUT Method
    @PutMapping
    public Map<String, String> putGreeting() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello World - PUT Method");
        return response;
    }

    // DELETE Method
    @DeleteMapping
    public Map<String, String> deleteGreeting() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello World - DELETE Method");
        return response;
    }
}
