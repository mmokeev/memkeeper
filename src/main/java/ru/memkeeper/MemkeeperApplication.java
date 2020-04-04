package ru.memkeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EntityScan(basePackageClasses = {ru.memkeeper.entities.ScanMarker.class})
@EnableJpaRepositories(basePackageClasses = {ru.memkeeper.repositories.ScanMarker.class})
@ComponentScan(basePackageClasses =
        {
                ru.memkeeper.setup.ScanMarker.class,
                ru.memkeeper.services.ScanMarker.class,
                ru.memkeeper.controllers.ScanMarker.class
        }
)
public class MemkeeperApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemkeeperApplication.class, args);
    }
}
