package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.domain.Coffee;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс IOServiceImpl")
@SpringBootTest
public class IOServiceImplTest {

    @Configuration
    static class MyConfiguration {

        private static final String coffeeOrdinal = String.valueOf(Coffee.LATTE.ordinal());
        private ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(coffeeOrdinal.getBytes());

        @Bean
        public IOService ioService() {

            return new IOServiceImpl(byteArrayInputStream);
        }
    }

    @Autowired
    private IOService ioService;

    @DisplayName("должен корректно считывать входную строку")
    @Test
    public void shouldHaveCorrectRead() {
        assertEquals(MyConfiguration.coffeeOrdinal, ioService.read());
    }

}
