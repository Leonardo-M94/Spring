package ru.itsjava.services;

import ru.itsjava.domain.Email;

import java.util.List;

public interface EmailService {
    long insert(Email email);

    List<Email> findAll();
}
