package ru.itsjava.repository;

import ru.itsjava.domain.Film;
import ru.itsjava.domain.Genre;

import java.util.List;

public interface FilmRepository {
    List<Film> findAll();

    List<Film> findByName(String filmName);

    List<Film> findByGenre(String genre);

    Film getById(long id);

    void insert(Film film);

    void update(Film film);

    void deleteById(long id);
}
