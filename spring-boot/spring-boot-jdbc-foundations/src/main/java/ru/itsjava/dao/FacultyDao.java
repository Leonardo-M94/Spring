package ru.itsjava.dao;

import ru.itsjava.domain.Faculty;

import java.util.List;
import java.util.Optional;

public interface FacultyDao {
    List<Faculty> findAll();

    Optional<Faculty> findByName(String name);
}
