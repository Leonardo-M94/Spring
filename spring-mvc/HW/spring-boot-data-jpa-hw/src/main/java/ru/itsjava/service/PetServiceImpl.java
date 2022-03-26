package ru.itsjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Pet;
import ru.itsjava.exception.AlreadyExistPetBreedException;
import ru.itsjava.repository.PetRepository;
import ru.itsjava.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void insert(Pet pet) throws AlreadyExistPetBreedException {
        Optional<Pet> optionalPet = petRepository.getByBreed(pet.getBreed());
        if (optionalPet.isPresent()) {
            throw new AlreadyExistPetBreedException("ERROR: Such pet breed already exists.");
        } else {
            petRepository.save(pet);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Pet> findByName(String name) {
        return petRepository.getByBreed(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Pet findById(long id) {
        return petRepository.getById(id);
    }

    @Transactional
    @Override
    public void delete(Pet pet) {
        userRepository.deleteAllByPet(pet);
        petRepository.delete(pet);
    }

    @Transactional
    @Override
    public void update(Pet pet) {
        petRepository.save(pet);
    }
}
