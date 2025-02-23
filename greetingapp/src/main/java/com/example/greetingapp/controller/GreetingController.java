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

    // GET Greeting by ID
    @GetMapping("/{id}")
    public Map<String, String> getGreetingById(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        greetingService.findGreetingById(id).ifPresentOrElse(
                greeting -> response.put("message", greeting.getMessage()),
                () -> response.put("error", "Greeting not found with ID: " + id)
        );
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

    // GET All Greetings
    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }
}
