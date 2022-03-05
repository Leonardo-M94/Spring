package ru.itsjava.services;

import ru.itsjava.domain.Email;
import ru.itsjava.exceptions.AlreadyExistEmailException;

import java.util.List;
import java.util.Optional;

public interface EmailService {
    long insert(Email email) throws AlreadyExistEmailException;

    Email findById(long id);

    Optional<Email> findByLogin(String email);

    List<Email> findAll();
}
