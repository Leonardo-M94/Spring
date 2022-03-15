package ru.itsjava.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Place;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(PlaceRepositoryImpl.class)
public class PlaceRepositoryImplTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PlaceRepository placeRepository;

    public static final long DEFAULT_FIRST_ID = 1L;
    public static final long DEFAULT_DELETED_ID = 2L;
    public static final long DEFAULT_NEW_ID = 4L;

    @Test
    public void shouldHaveCorrectGetById() {
        Place expectedPlace = entityManager.find(Place.class, DEFAULT_FIRST_ID);
        Place actualPlace = placeRepository.getById(DEFAULT_FIRST_ID);

        assertEquals(expectedPlace, actualPlace);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        Place expectedPlace = new Place(DEFAULT_NEW_ID, "Italy", DEFAULT_FIRST_ID);
        placeRepository.insert(expectedPlace);
        Place actualPlace = placeRepository.getById(DEFAULT_NEW_ID);

        assertEquals(expectedPlace, actualPlace);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        Place expectedPlace = placeRepository.getById(DEFAULT_FIRST_ID);
        expectedPlace.setName("The Alps");

        placeRepository.update(expectedPlace);
        Place actualPlace = placeRepository.getById(DEFAULT_FIRST_ID);

        assertEquals(expectedPlace.getName(), actualPlace.getName());
    }

    @Test
    public void shouldHaveCorrectDeleteById() {
        placeRepository.deleteById(DEFAULT_DELETED_ID);

        assertNull(placeRepository.getById(DEFAULT_DELETED_ID));
    }
}
