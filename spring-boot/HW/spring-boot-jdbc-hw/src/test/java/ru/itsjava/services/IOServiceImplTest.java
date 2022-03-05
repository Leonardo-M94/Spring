package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Класс IOServiceImpl")
@Import(IOServiceImpl.class)
@JdbcTest
public class IOServiceImplTest {

    @Configuration
    static class MyConfiguration {

        private static final String INPUT_LINE = "Hello";
        private static final int INPUT_NUM = 123;

        @Bean
        public IOService ioService() {

            IOService mockIOService = Mockito.mock(IOServiceImpl.class);
            when(mockIOService.input()).thenReturn(INPUT_LINE); // Задаем поведение.
            when(mockIOService.inputInt()).thenReturn(INPUT_NUM);

            return mockIOService;
        }
    }

    @Autowired
    private IOService ioService;

    @DisplayName("должен корректно считывать входную строку")
    @Test
    public void shouldHaveCorrectStringInput() {


        assertEquals(MyConfiguration.INPUT_LINE, ioService.input());
    }

    @DisplayName("должен корректно считывать входное число")
    @Test
    public void shouldHaveCorrectNumInput() {

        assertEquals(MyConfiguration.INPUT_NUM, ioService.inputInt());
    }
}
