package ru.itsjava.dao;

import ru.itsjava.domain.Email;

public interface EmailDao {

    int count();

    void insert(Email email);

    void update(Email email);   // Для отладки.

    void updatePassword(long id, String password);

    void deleteById(long id);

    void deleteByEmail(String email);

    Email findById(long id);
}
