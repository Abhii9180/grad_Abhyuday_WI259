package com.example.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class HelloWorldTask {

    // fixedRate = 10000 means 10 seconds (measured in milliseconds)
    @Scheduled(fixedRate = 10000)
    public void executeTask() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("Hello World! Current Time: " + dtf.format(LocalDateTime.now()));
    }
}