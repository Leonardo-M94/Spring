package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Pet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class PetRepositoryImpl implements PetRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Pet> findAll() {
        return entityManager.createQuery("select p from pets p", Pet.class).getResultList();
    }

    @Override
    public Optional<Pet> findByName(String name) {
        try {
            return Optional.ofNullable(entityManager.createQuery("select p from pets p where p.breed = ?1", Pet.class)
                    .setParameter(1, name)
                    .getSingleResult());
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public void insert(Pet pet) {
        if (pet.getId() == 0L) {
            entityManager.persist(pet);
        } else {
            entityManager.merge(pet);
        }
    }

    @Override
    public Pet getById(long id) {
        return entityManager.find(Pet.class, id);
    }

    @Override
    public void update(Pet pet) {
        if (pet.getId() == 0L) {
            entityManager.persist(pet);
        } else {
            entityManager.merge(pet);
        }
    }

    @Override
    public void deleteById(long id) {
        Pet deletedPet = entityManager.find(Pet.class, id);
        entityManager.remove(deletedPet);
    }
}
