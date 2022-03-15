package ru.itsjava.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(GenreRepositoryImpl.class)
public class GenreRepositoryImplTest {

    public static final long DEFAULT_UPDATED_GENRE_ID = 1L;
    public static final long DEFAULT_DELETED_GENRE_ID = 3L;
    public static final long DEFAULT_INSERTED_GENRE_ID = 4L;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void shouldHaveCorrectGetById() {
        Genre expectedGenre = entityManager.find(Genre.class, DEFAULT_UPDATED_GENRE_ID);
        Genre actualGenre = genreRepository.getById(DEFAULT_UPDATED_GENRE_ID);

        assertEquals(expectedGenre, actualGenre);
    }

    @Test
    public void shouldHaveCorrectFindAll() {
        List<Genre> expectedGenreList = entityManager.createQuery("select g from genre g", Genre.class).getResultList();
        List<Genre> actualGenreList = genreRepository.findAll();

        assertEquals(expectedGenreList, actualGenreList);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        var expectedGenre = new Genre(DEFAULT_INSERTED_GENRE_ID, "comedy");
        genreRepository.insert(expectedGenre);

        var actualGenre = genreRepository.getById(DEFAULT_INSERTED_GENRE_ID);

        assertEquals(expectedGenre, actualGenre);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        var expectedGenre = genreRepository.getById(DEFAULT_UPDATED_GENRE_ID);
        expectedGenre.setName("comedy");

        genreRepository.update(expectedGenre);
        var actualGenre = genreRepository.getById(DEFAULT_UPDATED_GENRE_ID);

        assertEquals(expectedGenre.getName(), actualGenre.getName());
    }

    @Test
    public void shouldHaveCorrectDeleteById() {
        genreRepository.deleteById(DEFAULT_DELETED_GENRE_ID);
        var deletedGenre = genreRepository.getById(DEFAULT_DELETED_GENRE_ID);

        assertNull(deletedGenre);
    }


}





















