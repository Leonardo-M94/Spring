package ru.itsjava.service;

import ru.itsjava.domain.User;

import java.util.List;

public interface UserService {
    long getCount();

    void insert(User user);

    void update(User user);

    void delete(User user);

    User findById(long id);

    List<User> findAll();
}
