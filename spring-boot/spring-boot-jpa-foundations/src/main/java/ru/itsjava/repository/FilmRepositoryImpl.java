package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Film;
import ru.itsjava.domain.Genre;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class FilmRepositoryImpl implements FilmRepository {

    private final EntityManager entityManager;

    @Override
    public List<Film> findAll() {   // JPQL - запрос по сущностям
        return entityManager
                .createQuery("select distinct f from films f join fetch f.genre join fetch f.places", Film.class)
                .getResultList();
    }

    @Override
    public List<Film> findByName(String filmName) {
        return entityManager
                .createQuery("select distinct f from films f join fetch f.genre join fetch f.places where f.title = ?1", Film.class)
                .setParameter(1, filmName)
                .getResultList();
    }

    @Override
    public List<Film> findByGenre(String genre) {
        return entityManager
                .createQuery("select distinct f from films f join fetch f.genre join fetch f.places where f.genre.name = ?1", Film.class)
                .setParameter(1, genre)
                .getResultList();
    }

    @Override
    public Film getById(long id) {
        return entityManager.find(Film.class, id);
    }

    @Override
    public void insert(Film film) {
        if (film.getId() == 0L) {
            entityManager.persist(film);
        } else {
            entityManager.merge(film);
        }
    }

    @Override
    public void update(Film film) {
        entityManager.merge(film);
    }

    @Override
    public void deleteById(long id) {
        Film deletedFilm = entityManager.find(Film.class, id);
        entityManager.remove(deletedFilm);
    }
}






















