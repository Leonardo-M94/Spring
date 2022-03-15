package ru.itsjava.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Film;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(FilmRepositoryImpl.class)
public class FilmRepositoryImplTest {

    public static final long DEFAULT_FILM_ID = 1L;

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
}



















