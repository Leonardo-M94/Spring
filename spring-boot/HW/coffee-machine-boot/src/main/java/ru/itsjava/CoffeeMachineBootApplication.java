package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itsjava.services.CoffeeMachine;

@SpringBootApplication
public class CoffeeMachineBootApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(CoffeeMachineBootApplication.class, args);

        CoffeeMachine coffeeMachine = context.getBean("coffeeMachine", CoffeeMachine.class);
        coffeeMachine.coffeeTransaction();
    }

}
