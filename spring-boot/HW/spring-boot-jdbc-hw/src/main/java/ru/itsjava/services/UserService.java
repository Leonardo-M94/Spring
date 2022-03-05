package ru.itsjava.services;

import ru.itsjava.domain.User;

import java.util.List;

public interface UserService {
    int getCount();

    long insert(User user);

    void update(User user);

    void delete(User user);

    User findById(long id);

    List<User> findAll();
}
