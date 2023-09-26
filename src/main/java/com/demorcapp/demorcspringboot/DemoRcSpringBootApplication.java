package com.demorcapp.demorcspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoRcSpringBootApplication {

    @GetMapping("/message")
    public String Message() {
        return "Congrats!! Your app deployed successfully in Azure!";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoRcSpringBootApplication.class, args);
    }

}
