package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.UserDao;
import ru.itsjava.domain.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public void insert(User user) {
        long id = userDao.insert(user);
        System.out.println("Новый user c id = " + id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
