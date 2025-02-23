package com.example.greetingapp.service;

import com.example.greetingapp.model.Greeting;
import com.example.greetingapp.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    @Autowired
    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    // Basic Greeting
    public String getGreetingMessage() {
        return "Hello World from Service Layer!";
    }

    // Personalized Greeting
    public String getPersonalizedGreeting(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return "Hello " + firstName + " " + lastName + "!";
        } else if (firstName != null) {
            return "Hello " + firstName + "!";
        } else if (lastName != null) {
            return "Hello " + lastName + "!";
        } else {
            return "Hello World!";
        }
    }

    // Save Greeting to Repository
    public Greeting saveGreeting(String message) {
        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    // Find Greeting by ID
    public Optional<Greeting> findGreetingById(Long id) {
        return greetingRepository.findById(id);
    }

    // List All Greetings
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }


}
