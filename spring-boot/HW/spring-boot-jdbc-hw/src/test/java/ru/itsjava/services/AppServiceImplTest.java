package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.itsjava.dao.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Класс AppServiceImpl")
@Import({AppServiceImpl.class, UserServiceImpl.class, EmailServiceImpl.class, PetServiceImpl.class, IOServiceImpl.class,
        UserDaoImpl.class, EmailDaoImpl.class, PetDaoImpl.class})
@JdbcTest
public class AppServiceImplTest {

    @Configuration
    static class MyConfiguration {

        @Bean
        public UserService userService(@Autowired UserDao userDao) {
            return new UserServiceImpl(userDao);
        }

        @Bean
        public EmailService emailService(@Autowired EmailDao emailDao) {
            return new EmailServiceImpl(emailDao);
        }

        @Bean
        public PetService petService(@Autowired PetDao petDao) {
            return new PetServiceImpl(petDao);
        }

        @Bean
        public IOService ioService() {
            IOService mockIOService = Mockito.mock(IOServiceImpl.class);
            when(mockIOService.inputInt()).thenReturn(1, 2, 3);
            when(mockIOService.input()).thenReturn("login", "password", "fio", "1990-10-12", "m", "Cat");

            return mockIOService;
        }

        @Bean
        public AppService appService(UserService userService, EmailService emailService, PetService petService, IOService ioService) {
            return new AppServiceImpl(userService, emailService, petService, ioService);
        }
    }

    @Autowired
    private AppService appService;

    @Value("${app-service-test.welcome}")
    private String expectedWelcome;

    @Value("${app-service-test.menu-out}")
    private String expectedMenuOut;

    @Value("${app-service-test.print-users}")
    private String expectedPrintAllUsers;

    @Value("${app-service-test.insert-users}")
    private String expectedInsertUser;

    @Value("${app-service-test.exit}")
    private String expectedExit;

    @Test
    public void shouldHaveCorrectStartApp() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String expectedOut = expectedWelcome + expectedMenuOut + expectedPrintAllUsers +
                expectedMenuOut + expectedInsertUser + expectedMenuOut + expectedExit;

        appService.start();

        assertEquals(expectedOut, out.toString());
    }
}
