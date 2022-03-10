package ru.itsjava.repository;

import ru.itsjava.domain.Genre;

import java.util.List;

public interface GenreRepository {

    List<Genre> findAll();

    Genre getById(long id);

    void insert(Genre genre);

    void update(Genre genre);

    void deleteById(long id);
}
