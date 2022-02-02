package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.itsjava.services.CoffeeMachine;

@ComponentScan("ru.itsjava.configuration")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        CoffeeMachine coffeeMachine = context.getBean("coffeeMachine", CoffeeMachine.class);
        coffeeMachine.coffeeTransaction();
    }
}
