package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJpaHwApplication {

    public static void main(String[] args) {

        var context = SpringApplication.run(SpringBootJpaHwApplication.class, args);
    }

}
