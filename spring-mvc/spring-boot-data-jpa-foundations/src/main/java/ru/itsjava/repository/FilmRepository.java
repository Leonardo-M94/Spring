package ru.itsjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsjava.domain.Film;
import ru.itsjava.domain.Genre;

import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {

    void deleteAllByGenre(Genre genre);

    Optional<Film> findFilmByTitleAndAndGenre(String title, Genre genre);
}
