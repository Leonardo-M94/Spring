package ru.itsjava.dao;

import ru.itsjava.domain.Pet;

import java.util.List;
import java.util.Optional;

public interface PetDao {
    long insert(Pet pet);
    List<Pet> findAll();
    Optional<Pet> findByName(String name);
}
