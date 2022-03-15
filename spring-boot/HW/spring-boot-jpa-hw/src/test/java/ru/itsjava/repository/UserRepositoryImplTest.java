package ru.itsjava.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Email;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(UserRepositoryImpl.class)
public class UserRepositoryImplTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    public static final long DEFAULT_FIRST_ID = 1L;
    public static final long DEFAULT_DELETED_ID = 2L;
    public static final String DEFAULT_NEW_FIO = "Zaicev Dima";

    public static final Pet DEFAULT_NEW_PET = new Pet(1, "Cat");
    public static final Email DEFAULT_NEW_EMAIL = new Email(4L, "Kovaleva.Anna.Vas@gmail.com", "fsds78REW78J");
    public static final User DEFAULT_NEW_USER = new User(3L, "Kovaleva Anna Vasilyevna", Date.valueOf("1989-01-23"), false,
            DEFAULT_NEW_EMAIL, DEFAULT_NEW_PET);
    @Test
    public void shouldHaveCorrectFindAll() {
        List<User> expectedUsersList = entityManager
                .createQuery("select distinct u from users u join fetch u.email join fetch u.pet", User.class)
                .getResultList();

        List<User> actualUsersList = userRepository.findAll();

        assertEquals(expectedUsersList, actualUsersList);
    }

    @Test
    public void shouldHaveCorrectFindByLogin() {
        User expectedUser = entityManager.find(User.class, DEFAULT_FIRST_ID);
        Optional<User> actualUser = userRepository.findByLogin(expectedUser.getEmail().getEmail());

        assertEquals(expectedUser, actualUser.get());
    }

    @Test
    public void shouldHaveCorrectGetById() {
        User expectedUser = entityManager.find(User.class, DEFAULT_FIRST_ID);
        User actualUser = userRepository.getById(DEFAULT_FIRST_ID);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        userRepository.insert(DEFAULT_NEW_USER);
        User actualUser = userRepository.getById(DEFAULT_NEW_USER.getId());

        assertEquals(DEFAULT_NEW_USER, actualUser);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        User expectedUser = userRepository.getById(DEFAULT_FIRST_ID);
        expectedUser.setFio(DEFAULT_NEW_FIO);

        userRepository.update(expectedUser);
        User actualUser = userRepository.getById(DEFAULT_FIRST_ID);

        assertEquals(expectedUser.getFio(), actualUser.getFio());
    }

    @Test
    public void shouldHaveCorrectDeleteById() {
        userRepository.deleteById(DEFAULT_DELETED_ID);
        var deletedUser = userRepository.getById(DEFAULT_DELETED_ID);

        assertNull(deletedUser);
    }
}
