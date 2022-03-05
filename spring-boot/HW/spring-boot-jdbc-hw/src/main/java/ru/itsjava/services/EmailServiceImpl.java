package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.EmailDao;
import ru.itsjava.domain.Email;
import ru.itsjava.exceptions.AlreadyExistEmailException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final EmailDao emailDao;

    @Override
    public long insert(Email email) throws AlreadyExistEmailException {
        // Проверяем уникальность логина
        Optional<Email> optionalEmail = emailDao.findByLogin(email.getEmail());
        if (optionalEmail.isPresent()) {
            throw new AlreadyExistEmailException("ERROR: Such email already exists.");
        } else {
            return emailDao.insert(email);
        }
    }

    @Override
    public Email findById(long id) {
        return emailDao.findById(id);
    }

    @Override
    public Optional<Email> findByLogin(String email) {
        return emailDao.findByLogin(email);
    }

    @Override
    public List<Email> findAll() {
        return emailDao.findAll();
    }
}
