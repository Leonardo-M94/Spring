package ru.itsjava.service;

import ru.itsjava.domain.Pet;
import ru.itsjava.exception.AlreadyExistPetBreedException;

import java.util.List;
import java.util.Optional;

public interface PetService {
    void insert(Pet pet) throws AlreadyExistPetBreedException;

    List<Pet> findAll();

    Optional<Pet> findByName(String name);
}
