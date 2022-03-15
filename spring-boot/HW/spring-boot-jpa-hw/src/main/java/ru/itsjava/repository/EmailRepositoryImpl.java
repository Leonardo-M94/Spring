package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Email;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class EmailRepositoryImpl implements EmailRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Email> findAll() {
        return entityManager.createQuery("select e from emails e", Email.class).getResultList();
    }

    @Override
    public Email getById(long id) {
        return entityManager.find(Email.class, id);
    }

    @Override
    public Optional<Email> getByEmail(String email) {
        try {
            return Optional.ofNullable(entityManager.createQuery(
                    "select e from emails e where e.email = ?1", Email.class)
                    .setParameter(1, email)
                    .getSingleResult());
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public void insert(Email email) {
        if (email.getId() == 0L) {
            entityManager.persist(email);
        } else {
            entityManager.merge(email);
        }
    }

    @Override
    public void update(Email email) {
        entityManager.merge(email);
    }

    @Override
    public void deleteById(long id) {
        Email deletedEmail = entityManager.find(Email.class, id);
        entityManager.remove(deletedEmail);
    }
}
