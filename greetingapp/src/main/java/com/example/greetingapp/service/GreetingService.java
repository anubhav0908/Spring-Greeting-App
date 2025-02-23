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



    // List All Greetings
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    // Update Greeting by ID
    public Greeting updateGreeting(Long id, String newMessage) {
        return greetingRepository.findById(id)
                .map(greeting -> {
                    greeting.setMessage(newMessage);
                    return greetingRepository.save(greeting);
                })
                .orElseThrow(() -> new RuntimeException("Greeting not found with ID: " + id));
    }

    // Delete Greeting by ID
    public String deleteGreeting(Long id) {
        return greetingRepository.findById(id)
                .map(greeting -> {
                    greetingRepository.delete(greeting);
                    return "Greeting deleted successfully with ID: " + id;
                })
                .orElseThrow(() -> new RuntimeException("Greeting not found with ID: " + id));
    }




}
