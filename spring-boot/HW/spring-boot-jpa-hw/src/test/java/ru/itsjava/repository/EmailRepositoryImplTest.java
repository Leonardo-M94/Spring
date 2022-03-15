package ru.itsjava.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Email;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(EmailRepositoryImpl.class)
public class EmailRepositoryImplTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmailRepository emailRepository;

    public static final long DEFAULT_FIRST_ID = 1L;
    public static final long DEFAULT_DELETED_ID = 2L;
    public static final long DEFAULT_NEW_ID = 4L;

    @Test
    public void shouldHaveCorrectFindAll() {

        List<Email> expectedEmailsList =
                entityManager.createQuery("select e from emails e", Email.class).getResultList();
        List<Email> actualEmailsList = emailRepository.findAll();

        assertEquals(expectedEmailsList, actualEmailsList);
    }

    @Test
    public void shouldHaveCorrectGetById() {
        Email expectedEmail = entityManager.find(Email.class, DEFAULT_FIRST_ID);
        Email actualEmail = emailRepository.getById(DEFAULT_FIRST_ID);

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void shouldHaveCorrectGetByEmail() {
        Email expectedEmail = entityManager.find(Email.class, DEFAULT_FIRST_ID);
        Optional<Email> actualEmail = emailRepository.getByEmail(expectedEmail.getEmail());

        assertEquals(expectedEmail, actualEmail.get());
    }

    @Test
    public void shouldHaveCorrectInsert() {
        Email expectedEmail = new Email(DEFAULT_NEW_ID, "qwerty123", "********");
        emailRepository.insert(expectedEmail);
        Email actualEmail = emailRepository.getById(DEFAULT_NEW_ID);

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        Email expectedEmail = emailRepository.getById(DEFAULT_FIRST_ID);

        expectedEmail.setPassword("Fjs720Ksq16");
        emailRepository.update(expectedEmail);

        Email actualEmail = emailRepository.getById(DEFAULT_FIRST_ID);

        assertEquals(expectedEmail.getPassword(), actualEmail.getPassword());
    }

    @Test
    public void shouldHaveCorrectDeleteById() {
        emailRepository.deleteById(DEFAULT_DELETED_ID);
        var deletedEmail = emailRepository.getById(DEFAULT_DELETED_ID);

        assertNull(deletedEmail);
    }
}
















