package ru.itsjava.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Film;
import ru.itsjava.domain.Genre;
import ru.itsjava.domain.Place;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(FilmRepositoryImpl.class)
public class FilmRepositoryImplTest {

    public static final long DEFAULT_FILM_ID = 1L;
    public static final long DEFAULT_DELETED_FILM_ID = 2L;
    public static final String DEFAULT_FILM_NAME = "Scream";
    public static final String DEFAULT_FILM_GENRE = "fantasy";

    public static final Place DEFAULT_NEW_PLACE = new Place(4L, "Germany", 3L);
    public static final Genre DEFAULT_NEW_GENRE = new Genre(4L, "fantastic");
    public static final Film DEFAULT_NEW_FILM = new Film(
            3L, "Star Trek",
            DEFAULT_NEW_GENRE,
            Arrays.asList(DEFAULT_NEW_PLACE)
    );

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private FilmRepository filmRepository;

    @Test
    public void shouldHaveCorrectGetById() {
        var expectedFilm = entityManager.find(Film.class, DEFAULT_FILM_ID);
        var actualFilm = filmRepository.getById(DEFAULT_FILM_ID);

        assertEquals(expectedFilm, actualFilm);
    }

    @Test
    public void shouldHaveCorrectFindAllFilms() {
        var expectedFilms = entityManager
                .createQuery("select distinct f from films f join fetch f.genre join fetch f.places", Film.class)
                .getResultList();

        var actualFilms = filmRepository.findAll();

        assertEquals(expectedFilms, actualFilms);
    }

    @Test
    public void shouldHaveCorrectFindByName() {
        var expectedFilmsList = entityManager
                .createQuery("select distinct f from films f join fetch f.genre join fetch f.places where f.title = ?1", Film.class)
                .setParameter(1, DEFAULT_FILM_NAME)
                .getResultList();
        var actualFilmsList = filmRepository.findByName(DEFAULT_FILM_NAME);

        assertEquals(expectedFilmsList, actualFilmsList);
    }

    @Test
    public void shouldHaveCorrectFindByGenre() {
        var expectedFilmsList = entityManager
                .createQuery("select distinct f from films f join fetch f.genre join fetch f.places where f.genre.name = ?1", Film.class)
                .setParameter(1, DEFAULT_FILM_GENRE)
                .getResultList();

        var actualFilmsList = filmRepository.findByGenre(DEFAULT_FILM_GENRE);

        assertEquals(expectedFilmsList, actualFilmsList);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        filmRepository.insert(DEFAULT_NEW_FILM);
        var actualFilm = filmRepository.getById(DEFAULT_NEW_FILM.getId());

        assertEquals(DEFAULT_NEW_FILM, actualFilm);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        var expectedFilm = filmRepository.getById(DEFAULT_FILM_ID);
        expectedFilm.setTitle(expectedFilm.getTitle() + ". Part II.");

        filmRepository.update(expectedFilm);
        var actualFilm = filmRepository.getById(DEFAULT_FILM_ID);

        assertEquals(expectedFilm.getTitle(), actualFilm.getTitle());
    }

    @Test
    public void shouldHaveCorrectDeleteById() {
        filmRepository.deleteById(DEFAULT_DELETED_FILM_ID);

        assertNull(filmRepository.getById(DEFAULT_DELETED_FILM_ID));
    }
}



















