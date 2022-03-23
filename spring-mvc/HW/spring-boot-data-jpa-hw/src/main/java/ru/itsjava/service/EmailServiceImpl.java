package ru.itsjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Email;
import ru.itsjava.exception.AlreadyExistEmailException;
import ru.itsjava.repository.EmailRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    @Transactional
    @Override
    public void insert(Email email) throws AlreadyExistEmailException {
        Optional<Email> optionalEmail = emailRepository.getByEmail(email.getEmail());
        if (optionalEmail.isPresent()) {
            throw new AlreadyExistEmailException("ERROR: Such email already exists.");
        } else {
            emailRepository.save(email);
        }
    }

    @Transactional
    @Override
    public void update(Email email) {
        Optional<Email> optionalEmail = emailRepository.getByEmail(email.getEmail());
        if (optionalEmail.isPresent()) {
            Email updatedEmail = optionalEmail.get();
            updatedEmail.setPassword(email.getPassword());
            emailRepository.save(updatedEmail);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Email findById(long id) {
        return emailRepository.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Email> findByLogin(String email) {
        return emailRepository.getByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Email> findAll() {
        return emailRepository.findAll();
    }
}
