package org.example.softwarefinal;

import org.example.softwarefinal.entity.ItemType;
import org.example.softwarefinal.entity.Salesman;
import org.example.softwarefinal.repository.SalesmanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class SoftwareFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftwareFinalApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(SalesmanRepository salesmanRepository) {
        return a -> {
            Salesman s = new Salesman(555, LocalDate.now(), ItemType.MUSIC_SYSTEM, "Hatice Islamoglu");
           salesmanRepository.save(s);
        };
    }

}
