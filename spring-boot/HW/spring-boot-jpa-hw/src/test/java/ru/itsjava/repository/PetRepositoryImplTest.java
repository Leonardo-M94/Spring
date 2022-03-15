package ru.itsjava.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(PetRepositoryImpl.class)
public class PetRepositoryImplTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PetRepository petRepository;

    public static final long DEFAULT_FIRST_ID = 1L;
    public static final long DEFAULT_DELETED_ID = 2L;
    public static final long DEFAULT_NEW_ID = 3L;

    @Test
    public void shouldHaveCorrectInsertPet() {
        Pet expectedPet = new Pet(DEFAULT_NEW_ID, "Snake");
        petRepository.insert(expectedPet);
        Pet actualPet = petRepository.getById(DEFAULT_NEW_ID);
        System.out.println("actualPet = " + actualPet);

        assertEquals(expectedPet, actualPet);
    }

    @Test
    public void shouldHaveCorrectGetById() {
        Pet expectedPet = entityManager.find(Pet.class, DEFAULT_FIRST_ID);
        Pet actualPet = petRepository.getById(DEFAULT_FIRST_ID);

        assertEquals(expectedPet, actualPet);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        Pet expectedPet = petRepository.getById(DEFAULT_FIRST_ID);
        expectedPet.setBreed("Wild cat");

        petRepository.update(expectedPet);
        Pet actualPet = petRepository.getById(DEFAULT_FIRST_ID);

        assertEquals(expectedPet.getBreed(), actualPet.getBreed());
    }

    @Test
    public void shouldHaveCorrectDeleteById() {
        petRepository.deleteById(DEFAULT_DELETED_ID);
        var deletedPet = petRepository.getById(DEFAULT_DELETED_ID);

        assertNull(deletedPet);
    }

    @Test
    public void shouldHaveCorrectFindByName() {
        Pet expectedPet = petRepository.getById(DEFAULT_FIRST_ID);
        Optional<Pet> actualPet = petRepository.findByName(expectedPet.getBreed());

        assertEquals(expectedPet, actualPet.get());
    }

    @Test
    public void shouldHaveCorrectFindAll() {

        List<Pet> expectedPetsList = entityManager.createQuery("select p from pets p", Pet.class).getResultList();
        List<Pet> actualPetsList = petRepository.findAll();

        assertEquals(expectedPetsList, actualPetsList);
    }
}

















