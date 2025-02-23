package com.example.greetingapp.controller;

import com.example.greetingapp.model.Greeting;
import com.example.greetingapp.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/greet")
public class GreetingController {

    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }


    // POST Method to Save Greeting
    @PostMapping
    public Greeting postGreeting(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        return greetingService.saveGreeting(message);
    }



    // PUT Method to Update Greeting
    @PutMapping("/{id}")
    public Greeting updateGreeting(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String newMessage = request.get("message");
        return greetingService.updateGreeting(id, newMessage);
    }


    // DELETE Method
    @DeleteMapping
    public Map<String, String> deleteGreeting() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello World - DELETE Method");
        return response;
    }

    // GET All Greetings
    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    // DELETE Method to Remove Greeting
    @DeleteMapping("/{id}")
    public Map<String, String> deleteGreeting(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            String result = greetingService.deleteGreeting(id);
            response.put("message", result);
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
        }
        return response;
    }



}
