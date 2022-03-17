package ru.itsjava.service;

import ru.itsjava.domain.Email;
import ru.itsjava.exception.AlreadyExistEmailException;

import java.util.List;
import java.util.Optional;

public interface EmailService {
    void insert(Email email) throws AlreadyExistEmailException;

    void update(Email email);

    Email findById(long id);

    Optional<Email> findByLogin(String email);

    List<Email> findAll();
}
