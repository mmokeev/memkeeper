package ru.memkeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;

@Import(MainConfiguration.class)
public class MemkeeperApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemkeeperApplication.class, args);
    }
}
