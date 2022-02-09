package ru.itsjava.services;

import org.springframework.stereotype.Service;
import ru.itsjava.domain.Film;

@Service
public class FilmServiceImpl implements FilmService {

    public FilmServiceImpl() {
        System.out.println("Constructor FilmServiceImpl");
    }

    @Override
    public Film getFilm() {
        System.out.println("Invoke getFilm()");
        return new Film("Back to the future");
    }
}
