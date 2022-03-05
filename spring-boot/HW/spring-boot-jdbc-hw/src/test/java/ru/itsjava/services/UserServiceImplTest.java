package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.itsjava.dao.UserDao;
import ru.itsjava.dao.UserDaoImpl;
import ru.itsjava.domain.Email;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Import({UserServiceImpl.class, UserDaoImpl.class})
@JdbcTest
@DisplayName("Класс UserServiceImpl")
public class UserServiceImplTest {

    @Configuration
    static class MyConfiguration {

        @Autowired
        private UserDao userDao;

        @Bean
        public UserService userService() {
            return new UserServiceImpl(userDao);
        }
    }

    @Autowired
    private UserService userService;

    public static final int DEFAULT_USER_COUNT = 2;
    public static final long DEFAULT_ID = 1L;
    public static final String DEFAULT_NAME = "Ilya Muromec";
    public static final Date DEFAULT_BIRTHDAY = Date.valueOf("2000-01-01");
    public static final Pet DEFAULT_PET = new Pet(1L, "Cat");
    public static final Email DEFAULT_EMAIL = new Email(3L, "Nik-Nik72@mail.ru", "qwerty123Jpk");
    public static final Email OLD_DEFAULT_EMAIL = new Email(1L, "user1-Ivanov@mail.ru", "r46RbUIN09");

    @DisplayName("должен корректно возвращать количество пользователей из БД")
    @Test
    public void shouldHaveCorrectGetCount() {
        int actualEmailCount = userService.getCount();

        assertEquals(DEFAULT_USER_COUNT, actualEmailCount);
    }

    @DisplayName("должен корректно добавлять нового пользователя в БД")
    @Test
    public void shouldHaveCorrectInsert() {
        User expectedUser = new User(DEFAULT_NAME, DEFAULT_BIRTHDAY, true, DEFAULT_EMAIL, DEFAULT_PET);

        long expectedEmailId = userService.insert(expectedUser);

        expectedUser.setId(expectedEmailId);

        User actualUser = userService.findById(expectedEmailId);

        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
    }

    @DisplayName("должен корректно обновлять запись пользователя в БД")
    @Test
    public void shouldHaveCorrectUpdate() {
        User expectedUser = new User(DEFAULT_ID, DEFAULT_NAME, DEFAULT_BIRTHDAY, true, OLD_DEFAULT_EMAIL, DEFAULT_PET);
        userService.update(expectedUser);
        User actualUser = userService.findById(DEFAULT_ID);

        assertEquals(expectedUser, actualUser);
    }

    @DisplayName("должен корректно удалять пользователя из БД")
    @Test
    public void shouldHaveCorrectDelete() {
        int expectedUserCount = userService.getCount();
        User user = userService.findById(DEFAULT_ID);
        userService.delete(user);

        assertEquals(expectedUserCount - 1, userService.getCount());
    }

    @DisplayName("должен корректно возвращать из БД список всех пользователей")
    @Test
    public void shouldHaveCorrectGetUserList() {
        List<User> usersList = userService.findAll();

        String expectedUserListValue = "[User{id=1, fio='Ivanov Ivan Ivanovich', birthday=1990-11-10, male=true, " +
                "Email(id=1, email=user1-Ivanov@mail.ru, password=r46RbUIN09), Pet(id=1, breed=Cat)}, " +
                "User{id=2, fio='Petrov Anton Mihaylovich', birthday=1995-07-19, male=true, " +
                "Email(id=2, email=Ant.Petrov-95@yandex.ru, password=dki23ERS8323), Pet(id=2, breed=Dog)}]";

        assertAll(() -> assertEquals(DEFAULT_USER_COUNT, usersList.size()),
                () -> assertEquals(expectedUserListValue, usersList.toString()));
    }
}
