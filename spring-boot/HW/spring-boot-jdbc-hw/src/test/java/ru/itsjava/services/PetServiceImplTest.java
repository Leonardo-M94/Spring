package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.itsjava.dao.PetDao;
import ru.itsjava.dao.PetDaoImpl;
import ru.itsjava.domain.Pet;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс PetServiceImpl")
@Import({PetServiceImpl.class, PetDaoImpl.class})
@JdbcTest
public class PetServiceImplTest {

    @Configuration
    static class MyConfiguration {
        @Autowired
        private PetDao petDao;

        @Bean
        public PetService petService() {
            return new PetServiceImpl(petDao);
        }
    }

    @Autowired
    private PetService petService;

    private static final int DEFAULT_PETS_COUNT = 2;
    private static final String NEW_BREED = "Snake";

    @DisplayName("должен корректно добавлять новую породу животного в БД")
    @Test
    public void shouldHaveCorrectInsert() {
        Pet expectedPet = new Pet(NEW_BREED);

        long idExpected = petService.insert(expectedPet);

        expectedPet.setId(idExpected);

        final Optional<Pet> optionalPet = petService.findByName(NEW_BREED);

        assertEquals(expectedPet, optionalPet.get());
    }

    @DisplayName("должен корректно выдавать из БД список всех пород")
    @Test
    public void shouldHaveCorrectFindAllPets() {

        String petsListValue = "[Pet(id=1, breed=Cat), Pet(id=2, breed=Dog)]";

        final List<Pet> petsList = petService.findAll();

        assertAll(
                () -> assertEquals(DEFAULT_PETS_COUNT, petsList.size()),
                () -> assertEquals(petsListValue, petsList.toString())
        );
    }
}
