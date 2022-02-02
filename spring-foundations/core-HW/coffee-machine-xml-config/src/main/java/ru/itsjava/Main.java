package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itsjava.services.CoffeeMachine;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        CoffeeMachine coffeeMachine = context.getBean("coffeeMachine", CoffeeMachine.class);
        coffeeMachine.coffeeTransaction();
    }
}
