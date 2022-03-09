package ru.itsjava;

import lombok.SneakyThrows;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJpaFoundationsApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaFoundationsApplication.class, args);
        Console.main(args);
    }

}
