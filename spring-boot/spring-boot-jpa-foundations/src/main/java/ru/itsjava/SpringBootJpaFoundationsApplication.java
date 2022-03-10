package ru.itsjava;

import lombok.SneakyThrows;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.domain.Film;
import ru.itsjava.domain.Genre;
import ru.itsjava.domain.Place;
import ru.itsjava.repository.FilmRepository;
import ru.itsjava.repository.GenreRepository;
import ru.itsjava.repository.PlaceRepository;

@SpringBootApplication
public class SpringBootJpaFoundationsApplication {

    @SneakyThrows
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBootJpaFoundationsApplication.class, args);
        GenreRepository genreRepository = context.getBean(GenreRepository.class);
        System.out.println("genreRepository.getById(1L) = " + genreRepository.getById(1L));

        Genre genre = new Genre(0L, "western");
        genreRepository.insert(genre);
        System.out.println("genreRepository.getById(3L) = " + genreRepository.getById(3L));

        Genre genre3 = genreRepository.getById(3L);
        genre3.setName("WESTERN");

        genreRepository.update(genre3);
        System.out.println("genreRepository.getById(3L) = " + genreRepository.getById(3L));

        genreRepository.deleteById(3L);
        System.out.println("genreRepository.getById(3L) = " + genreRepository.getById(3L));

        System.out.println("genreRepository.findAll() = " + genreRepository.findAll());


        FilmRepository filmRepository = context.getBean(FilmRepository.class);
        System.out.println(filmRepository.findAll());

        System.out.println("filmRepository.findByName(\"Harry Potter\") = " + filmRepository.findByName("Harry Potter"));

        System.out.println("filmRepository.findByGenre(\"fantasy\") = " + filmRepository.findByGenre("fantasy"));


        PlaceRepository placeRepository = context.getBean(PlaceRepository.class);

        placeRepository.insert(new Place(0L, "France", 3L));
        System.out.println("placeRepository.getById(5L) = " + placeRepository.getById(5L));

        placeRepository.deleteById(5L);
        System.out.println("After delete 5L = " + placeRepository.getById(5L));

//        Console.main(args);
    }

}



















