package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.EmailDao;
import ru.itsjava.domain.Email;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final EmailDao emailDao;

    @Override
    public void insert(Email email) {
        long id = emailDao.insert(email);
        System.out.println("Новый email c id = " + id);
    }
}
