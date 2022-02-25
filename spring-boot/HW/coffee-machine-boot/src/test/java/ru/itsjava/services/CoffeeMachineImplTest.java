package ru.itsjava.services;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.exceptions.ResourceExhaustionException;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("Класс CoffeeMachineImpl")
@SpringBootTest
public class CoffeeMachineImplTest {

    @Configuration
    static class MyConfiguration {

        @Bean
        public CoffeeService coffeeService() {
            return new CoffeeServiceImpl();
        }

        @Bean
        public IOService ioService() {
            IOService ioService = Mockito.mock(IOServiceImpl.class);
            when(ioService.read()).thenReturn("100", "1");

            return ioService;
        }

        @Bean
        public CoffeeMachine coffeeMachine(CoffeeService coffeeService, IOService ioService) {
            return new CoffeeMachineImpl(coffeeService, ioService);
        }
    }

    @Autowired
    private CoffeeMachine coffeeMachine;


    @DisplayName("должен корректно выполнять транзакцию по приготовлению кофе")
    @Test
    public void shouldHaveCorrectExecuteCoffeeTransaction() {

        assertTrue(coffeeMachine.coffeeTransaction());                                                                // Americano
    }


    @SneakyThrows
    @DisplayName("должен корректно определять наличие ресурсов")
    @Test
    public void shouldHaveCorrectCheckResourcesAvailability() {

        Field resources = coffeeMachine.getClass().getDeclaredField("resources");
        resources.setAccessible(true);
        resources.set(coffeeMachine, (int) 300);

        assertTrue(coffeeMachine.isResourcesEnough());
    }


    @SneakyThrows
    @DisplayName("должен корректно поймать исключение об отсутствии ресурсов")
    @Test
    public void shouldHaveCorrectCatchResourceExhaustionException() {

        Field resources = coffeeMachine.getClass().getDeclaredField("resources");
        resources.setAccessible(true);
        resources.set(coffeeMachine, (int) 0);

        Throwable thrown = assertThrows(
                ResourceExhaustionException.class,
                () -> coffeeMachine.isResourcesEnough());
        assertEquals("Coffee resources have run out at machine.", thrown.getMessage());
    }

}
