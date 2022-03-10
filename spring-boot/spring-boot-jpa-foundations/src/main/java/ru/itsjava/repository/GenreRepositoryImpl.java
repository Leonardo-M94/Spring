package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Genre;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class GenreRepositoryImpl implements GenreRepository {
    private final EntityManager entityManager;

    @Override
    public Genre getById(long id) {
        return entityManager.find(Genre.class, id);
    }

    @Override
    public void insert(Genre genre) {
        if (genre.getId() == 0L) {
            entityManager.persist(genre);
        } else {
            entityManager.merge(genre);
        }
    }

    @Override
    public void update(Genre genre) {
        entityManager.merge(genre);
    }

    @Override
    public List<Genre> findAll() {
        return entityManager.createQuery("select g from genre g", Genre.class).getResultList();
    }

    @Override
    public void deleteById(long id) {
        Genre genre = entityManager.find(Genre.class, id);
        entityManager.remove(genre);
    }
}














