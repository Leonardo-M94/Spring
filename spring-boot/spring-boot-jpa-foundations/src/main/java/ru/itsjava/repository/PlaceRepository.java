package ru.itsjava.repository;

import ru.itsjava.domain.Place;

public interface PlaceRepository {
    Place getById(long id);

    void insert(Place place);

    void update(Place place);

    void deleteById(long id);
}
