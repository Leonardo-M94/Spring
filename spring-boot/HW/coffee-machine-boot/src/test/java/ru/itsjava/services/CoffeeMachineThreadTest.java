package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.thread.CoffeeMachineThread;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Класс CoffeeMachineThread")
@SpringBootTest
public class CoffeeMachineThreadTest {

    @Configuration
    static class MyConfiguration {

        @Bean
        public CoffeeService coffeeService() {
            return new CoffeeServiceImpl();
        }

        @Bean
        public IOService ioService() {
            IOService ioService = Mockito.mock(IOServiceImpl.class);
            when(ioService.read()).thenReturn("100", "1", "50", "2");

            return ioService;
        }

        @Bean
        public CoffeeMachine coffeeMachine(CoffeeService coffeeService, IOService ioService) {
            return new CoffeeMachineImpl(coffeeService, ioService);
        }

        @Bean
        public CoffeeMachineThread coffeeMachineThread(CoffeeMachine coffeeMachine) {
            return new CoffeeMachineThread(coffeeMachine);
        }
    }

    @Autowired
    private CoffeeMachineThread coffeeMachineThread;

    @DisplayName("должен корректно запускать поток")
    @Test
    public void shouldHaveCorrectThreadRun() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        coffeeMachineThread.run();
        String[] outLines = out.toString().split("\n");

        assertEquals("Error: Coffee resources have run out at machine.\r",
                outLines[outLines.length - 1]);
    }
}
