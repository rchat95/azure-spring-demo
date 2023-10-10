package com.demorcapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demorcapp.database.UserRepository;
import com.demorcapp.model.User;

@SpringBootApplication
@RestController
public class DemoRcSpringBootApplication {

    private final UserRepository userRepository;

    public DemoRcSpringBootApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/message")
    public String Message() {
        return "Congrats!! Your app deployed successfully in Azure!";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoRcSpringBootApplication.class, args);
    }

    @PostMapping("/register2")
    public String registerUser(@RequestBody User user) {
        // Perform validation and save user to the database
        userRepository.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login2")
    public String loginUser(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "Login successful!";
        } else {
            return "Invalid credentials. Please try again.";
        }
    }

}
