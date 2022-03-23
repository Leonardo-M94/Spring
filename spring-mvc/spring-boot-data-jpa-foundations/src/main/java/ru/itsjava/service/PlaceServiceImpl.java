package ru.itsjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.repository.PlaceRepository;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional(readOnly = true)
    @Override
    public void printAll() {
        System.out.println(placeRepository.findAll().toString());
    }
}
