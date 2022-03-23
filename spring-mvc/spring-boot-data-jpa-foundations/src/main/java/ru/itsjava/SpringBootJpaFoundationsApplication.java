package ru.itsjava;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJpaFoundationsApplication {

    @SneakyThrows
    public static void main(String[] args) {
        var context = SpringApplication.run(SpringBootJpaFoundationsApplication.class, args);
    }

}



















