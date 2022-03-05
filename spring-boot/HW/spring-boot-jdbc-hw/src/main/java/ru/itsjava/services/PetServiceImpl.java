package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.PetDao;
import ru.itsjava.domain.Pet;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

    private final PetDao petDao;

    @Override
    public long insert(Pet pet) {
        return petDao.insert(pet);
    }

    @Override
    public List<Pet> findAll() {
        return petDao.findAll();
    }

    @Override
    public Optional<Pet> findByName(String name) {
        return petDao.findByName(name);
    }
}
