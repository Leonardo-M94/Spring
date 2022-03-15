package ru.itsjava.repository;

import ru.itsjava.domain.Email;

import java.util.List;
import java.util.Optional;

public interface EmailRepository {

    List<Email> findAll();

    Email getById(long id);

    Optional<Email> getByEmail(String email);

    void insert(Email email);

    void update(Email email);

    void deleteById(long id);
}
