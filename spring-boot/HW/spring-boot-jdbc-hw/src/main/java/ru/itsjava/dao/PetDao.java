package ru.itsjava.dao;

import ru.itsjava.domain.Pet;

import java.util.List;

public interface PetDao {
    List<Pet> findAll();
    Pet findByName(String name);
}
