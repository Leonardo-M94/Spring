package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.FacultyDao;
import ru.itsjava.domain.Faculty;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyDao facultyDao;

    @Override
    public List<Faculty> findAll() {
        return facultyDao.findAll();
    }

    @Override
    public Optional<Faculty> findByName(String name) {
        return facultyDao.findByName(name);
    }
}
