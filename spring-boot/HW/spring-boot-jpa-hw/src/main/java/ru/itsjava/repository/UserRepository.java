package ru.itsjava.repository;

import ru.itsjava.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findByLogin(String login);

    User getById(long id);

    void insert(User user);

    void update(User user);

    void deleteById(long id);
}
