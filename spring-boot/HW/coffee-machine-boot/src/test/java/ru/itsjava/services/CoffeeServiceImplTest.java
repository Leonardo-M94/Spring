package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.domain.Coffee;
import ru.itsjava.exceptions.InvalidCoffeeIndexException;
import ru.itsjava.exceptions.OperationCancelException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Класс CoffeeServiceImpl")
public class CoffeeServiceImplTest {

    @Configuration
    static class MyConfiguration {

        @Bean
        public CoffeeService coffeeService() {
            return new CoffeeServiceImpl();
        }
    }

    @Autowired
    private CoffeeService coffeeService;

    private final Coffee testCoffee = Coffee.AMERICANO;
    private final double payment = 100.0;
    private static final int CANCEL = 0;
    private static final int INVALID_INDEX = -1;

    @DisplayName("должен корректно возвращать тип кофе по индексу")
    @Test
    public void shouldHaveCorrectReturnCoffeeTypeByIndex() {
                                    // Принимает индексы кофе, начиная с 1.
        assertEquals(               // 0 - отмена операции, см. след. тест.
                testCoffee,
                coffeeService.getCoffeeByIndex(testCoffee.ordinal() + 1)
        );
    }

    @DisplayName("должен корректно поймать исключение по отмене операции")
    @Test
    public void shouldHaveCorrectCatchOperationCancelException() {

        Throwable thrown = assertThrows(
                OperationCancelException.class,
                () -> coffeeService.getCoffeeByIndex(CANCEL)
        );
        assertNotNull(thrown.getMessage());
    }

    @DisplayName("должен корректно поймать исключение о неправильном индексе")
    @Test
    public void shouldHaveCorrectCatchInvalidCoffeeIndexException() {

        Throwable thrown = assertThrows(
                InvalidCoffeeIndexException.class,
                () -> coffeeService.getCoffeeByIndex(INVALID_INDEX));
        assertEquals("Invalid coffee index!", thrown.getMessage());
    }


    @DisplayName("должен корректно выводить кофейную карту")
    @Test
    public void shouldHaveCorrectPrintCoffeeCard() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        coffeeService.showCoffeeBar();

        assertEquals("1) AMERICANO: 35.0\r\n" +
                "2) LATTE: 50.0\r\n" +
                "3) CAPPUCCINO: 50.0\r\n" +
                "4) ESPRESSO: 30.0\r\n" +
                "5) CHOCOLATE: 35.0\r\n" +
                "0) CANCEL OPERATION\r\n", out.toString());
    }

    @DisplayName("должен корректно возвращать сдачу за кофе")
    @Test
    public void shouldHaveCorrectReturnChange() {

        assertEquals(
                payment - testCoffee.getPrice(),
                coffeeService.checkCoffeeCost(payment, testCoffee));
    }

}
