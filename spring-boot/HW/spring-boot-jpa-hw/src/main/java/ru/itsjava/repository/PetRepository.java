package ru.itsjava.repository;

import ru.itsjava.domain.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository {

    List<Pet> findAll();

    Optional<Pet> findByName(String name);

    Pet getById(long id);

    void insert(Pet pet);

    void update(Pet pet);

    void deleteById(long id);
}
