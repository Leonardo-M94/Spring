package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Place;

import javax.persistence.EntityManager;

@Repository
@Transactional
@RequiredArgsConstructor
public class PlaceRepositoryImpl implements PlaceRepository {

    private final EntityManager entityManager;

    @Override
    public Place getById(long id) {
        return entityManager.find(Place.class, id);
    }

    @Override
    public void insert(Place place) {
        if (place.getId() == 0L) {
            entityManager.persist(place);
        } else {
            entityManager.merge(place);
        }
    }

    @Override
    public void update(Place place) {
        entityManager.merge(place);
    }

    @Override
    public void deleteById(long id) {
        Place deletedPlace = entityManager.find(Place.class, id);
        entityManager.remove(deletedPlace);
    }
}
