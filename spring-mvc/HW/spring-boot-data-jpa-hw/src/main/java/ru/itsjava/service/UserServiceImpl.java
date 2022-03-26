package ru.itsjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.User;
import ru.itsjava.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public long getCount() {
        return userRepository.count();
    }

    @Transactional
    @Override
    public void insert(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void update(User user) {
//        Optional<User> optionalUser = userRepository.getByEmail(user.getEmail());
//        if (optionalUser.isPresent()) {
//            User updatedUser = optionalUser.get();
//            updatedUser.setFio();
//            }
//
//        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);

    }

    @Transactional(readOnly = true)
    @Override
    public User findById(long id) {
        return userRepository.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
