package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.itsjava.dao.EmailDao;
import ru.itsjava.dao.EmailDaoImpl;
import ru.itsjava.domain.Email;
import ru.itsjava.exceptions.AlreadyExistEmailException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс EmailServiceImpl")
@Import({EmailServiceImpl.class, EmailDaoImpl.class})
@JdbcTest
public class EmailServiceImplTest {

    @Configuration
    static class MyConfiguration {
        @Autowired
        private EmailDao emailDao;

        @Bean
        EmailService emailService() {
            return new EmailServiceImpl(emailDao);
        }
    }

    @Autowired
    private EmailService emailService;

    private static final long DEFAULT_ID = 1L;
    private static final String NEW_EMAIL = "qwerty-123@ya.ru";
    private static final String EXISTS_EMAIL = "user1-Ivanov@mail.ru";
    private static final String DEFAULT_PASSWORD = "r46RbUIN09";
    private static final int DEFAULT_EMAILS_COUNT = 3;

    @DisplayName("должен корректно добавлять новый аккаунт в БД")
    @Test
    public void shouldHaveCorrectInsertEmail() {


        Email expectedEmail = new Email(NEW_EMAIL, DEFAULT_PASSWORD);

        long emailId = emailService.insert(expectedEmail);

        expectedEmail.setId(emailId);

        Email actualEmail = emailService.findById(emailId);

        assertEquals(expectedEmail, actualEmail);
    }

    @DisplayName("должен корректно поймать исключение о существующем в БД email")
    @Test
    public void shouldHaveCorrectCatchAlreadyExistEmailException() {

        Throwable thrown = assertThrows(
                AlreadyExistEmailException.class,
                () -> emailService.insert(new Email(EXISTS_EMAIL, DEFAULT_PASSWORD))
        );
        assertNotNull(thrown.getMessage());
    }

    @DisplayName("должен корректно выдавать из БД аккаунт по логину")
    @Test
    public void shouldHaveCorrectFindEmailByLogin() {
        Email expectedEmail = new Email(DEFAULT_ID, EXISTS_EMAIL, DEFAULT_PASSWORD);

        final Optional<Email> optionalEmail = emailService.findByLogin(EXISTS_EMAIL);

        assertEquals(expectedEmail, optionalEmail.get());
    }

    @DisplayName("должен корректно выдавать из БД список всех аккаунтов")
    @Test
    public void shouldHaveCorrectFindAllEmails() {

        String emailsListValue = "[Email(id=1, email=user1-Ivanov@mail.ru, password=r46RbUIN09), " +
                "Email(id=2, email=Ant.Petrov-95@yandex.ru, password=dki23ERS8323), " +
                "Email(id=3, email=Nik-Nik72@mail.ru, password=qwerty123Jpk)]";

        List<Email> emailsList = emailService.findAll();

        assertAll(
                () -> assertEquals(DEFAULT_EMAILS_COUNT, emailsList.size()),
                () -> assertEquals(emailsListValue, emailsList.toString())
        );
    }

}
