package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Email;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(EmailDaoImpl.class)
public class EmailJdbcDaoImplTest {

    public static final String DEFAULT_EMAIL = "UPDATED-EMAIL@ya.ru";
    public static final long NEW_ID = 3L;
    public static final String DEFAULT_PASSWORD = "GOOD PASSWORD";
    public static final String DEFAULT_NAME = "Who I am";
    public static final long FIRST_ID = 1L;
    public static final String NEW_PASSWORD = "*********";
    @Autowired
    private EmailDao emailDao;

    @Test
    public void shouldHaveCorrectGetCount() {
        int actualEmailCount = emailDao.count();

        assertEquals(2, actualEmailCount);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        Email expectedEmail = new Email(NEW_ID, DEFAULT_EMAIL, DEFAULT_PASSWORD, DEFAULT_NAME,
                Date.valueOf("2000-01-01"), true);
        emailDao.insert(expectedEmail);
        Email actualEmail = emailDao.findById(NEW_ID);

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        Email expectedEmail = new Email(FIRST_ID, DEFAULT_EMAIL, DEFAULT_PASSWORD, DEFAULT_NAME,
                Date.valueOf("2000-01-01"), true);
        emailDao.update(expectedEmail);
        Email actualEmail = emailDao.findById(FIRST_ID);

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void shouldHaveCorrectUpdatePassword() {
        emailDao.updatePassword(FIRST_ID, NEW_PASSWORD);
        String actualPassword = emailDao.findById(FIRST_ID).getPassword();

        assertEquals(NEW_PASSWORD, actualPassword);
    }

    @Test
    public void shouldHaveCorrectDeleteById() {
        emailDao.deleteById(FIRST_ID);

        assertEquals(1, emailDao.count());
    }

    @Test
    public void shouldHaveCorrectDeleteByEmail() {
        Email deletedEmail = emailDao.findById(FIRST_ID);
        emailDao.deleteByEmail(deletedEmail.getEmail());

        assertEquals(1, emailDao.count());
    }
}