package ru.itsjava.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.services.*;

@Configuration
public class MyConfig {
    @Bean
    public CoffeeService coffeeService() {
        return new CoffeeServiceImpl();
    }

    @Bean
    public IOService ioService() {
        return new IOServiceImpl();
    }

    @Bean
    public CoffeeMachine coffeeMachine(CoffeeService coffeeService, IOService ioService) {
        return new CoffeeMachineImpl(coffeeService, ioService);
    }
}
