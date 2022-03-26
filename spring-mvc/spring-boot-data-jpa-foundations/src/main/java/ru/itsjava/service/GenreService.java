package ru.itsjava.service;

import ru.itsjava.domain.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAllGenres();

    void changeGenre(String oldName, String updatedName);

    void updateGenre(Genre genre);

    void printGenre(String name);

    void createGenre(Genre genre);

    Genre getGenreById(long id);

    void deleteGenre(Genre genre);

    void deleteGenreById(long id);
}
