package ru.itsjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsjava.domain.Email;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {

    Optional<Email> getByEmail(String email);
}
