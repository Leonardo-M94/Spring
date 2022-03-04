package ru.itsjava.dao;

import ru.itsjava.domain.Email;

import java.util.List;

public interface EmailDao {
    int count();

    long insert(Email email);

    void update(Email email);

    void delete(Email email);

    Email findById(long id);

    Email findByLogin(String email);

    List<Email> findAll();
}
