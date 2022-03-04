package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.EmailDao;
import ru.itsjava.domain.Email;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final EmailDao emailDao;

    @Override
    public long insert(Email email) {
        return emailDao.insert(email);
    }

    @Override
    public List<Email> findAll() {
        return emailDao.findAll();
    }
}
