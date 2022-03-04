package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Email;
import ru.itsjava.domain.User;
import ru.itsjava.domain.Pet;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(UserDaoImpl.class)
public class UserJdbcDaoImplTest {

    public static final long DEFAULT_ID = 1L;
    public static final String DEFAULT_NAME = "Ilya Muromec";
    public static final Date DEFAULT_BIRTHDAY = Date.valueOf("2000-01-01");
    public static final Pet DEFAULT_PET = new Pet(1L, "Мейкун");
    public static final Email DEFAULT_EMAIL = new Email(3L, "Nik-Nik72@mail.ru", "qwerty123Jpk");
    public static final Email OLD_DEFAULT_EMAIL = new Email(1L, "user1-Ivanov@mail.ru", "r46RbUIN09");

    @Autowired
    private UserDao userDao;

    @Test
    public void shouldHaveCorrectGetCount() {
        int actualEmailCount = userDao.count();

        assertEquals(2, actualEmailCount);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        User expectedUser = new User(DEFAULT_NAME, DEFAULT_BIRTHDAY, true, DEFAULT_EMAIL, DEFAULT_PET);

        long expectedEmailId = userDao.insert(expectedUser);

        User actualUser = userDao.findById(expectedEmailId);

        expectedUser.setId(expectedEmailId);

        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        User expectedUser = new User(DEFAULT_ID, DEFAULT_NAME, DEFAULT_BIRTHDAY, true, OLD_DEFAULT_EMAIL, DEFAULT_PET);
        userDao.update(expectedUser);
        User actualUser = userDao.findById(DEFAULT_ID);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void shouldHaveCorrectDelete() {
        int expectedUserCount = userDao.count();
        User user = userDao.findById(DEFAULT_ID);
        userDao.delete(user);

        assertEquals(expectedUserCount - 1, userDao.count());
    }
}