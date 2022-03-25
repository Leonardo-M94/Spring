package ru.itsjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Genre;
import ru.itsjava.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    @Override
    public void printGenre(String name) {
        Genre genre = genreRepository.getByName(name).get();
        System.out.println(genre);
    }

    @Transactional
    @Override
    public void createGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getGenreById(long id) {
        return genreRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void deleteGenreById(long id) {
        genreRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Transactional
    @Override
    public void changeGenre(String oldName, String updatedName) {
        Genre genre = genreRepository.getByName(oldName).get();
        genre.setName(updatedName);
        genreRepository.save(genre);
        System.out.println("Successfully saved!");
    }
}
