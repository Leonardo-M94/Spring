package ru.itsjava.services;

import ru.itsjava.domain.Faculty;

import java.util.List;
import java.util.Optional;

public interface FacultyService {
    List<Faculty> findAll();

    Optional<Faculty> findByName(String name);
}
