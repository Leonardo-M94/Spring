package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CoffeeMachineBootApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(CoffeeMachineBootApplication.class, args);

        Thread coffeeThread = context.getBean("coffeeThread", Thread.class);
        coffeeThread.start();
    }

}
