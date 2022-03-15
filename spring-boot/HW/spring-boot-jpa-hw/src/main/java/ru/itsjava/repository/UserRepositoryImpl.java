package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select distinct u from users u join fetch u.email join fetch u.pet", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try {
            return Optional.ofNullable(entityManager.createQuery(
                    "select distinct u from users u join fetch u.email join fetch u.pet where u.email.email = ?1", User.class)
                    .setParameter(1, login)
                    .getSingleResult());
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void insert(User user) {
        if (user.getId() == 0L) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteById(long id) {
        User deletedUser = entityManager.find(User.class, id);
        entityManager.remove(deletedUser);
    }
}
