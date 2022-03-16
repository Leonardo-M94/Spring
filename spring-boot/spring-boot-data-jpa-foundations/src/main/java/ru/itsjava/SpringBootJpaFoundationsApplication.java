package ru.itsjava;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
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
        genreRepository.save(genre);
        System.out.println("genreRepository.getById(3L) = " + genreRepository.getById(3L));

        Genre genre3 = genreRepository.getById(3L);
        genre3.setName("WESTERN");

        genreRepository.save(genre3);
        System.out.println("genreRepository.getById(3L) = " + genreRepository.getById(3L));

        genreRepository.deleteById(3L);
        System.out.println("genreRepository.findById(3L).isPresent() = " + genreRepository.findById(3L).isPresent());

        System.out.println("genreRepository.findAll() = " + genreRepository.findAll());


        FilmRepository filmRepository = context.getBean(FilmRepository.class);
        System.out.println(filmRepository.findAll());

        PlaceRepository placeRepository = context.getBean(PlaceRepository.class);

        placeRepository.save(new Place(0L, "France", 3L));
        System.out.println("placeRepository.getById(4L) = " + placeRepository.getById(4L));

        placeRepository.deleteById(4L);
        System.out.println("After delete 4L = " + placeRepository.findById(4L).isPresent());

        System.out.println("genreRepository.getByName(\"WESTERN\").isPresent() = " + genreRepository.getByName("WESTERN").isPresent());

        System.out.println("filmRepository.findFilmByTitleAndAndGenre(\"Harry Potter\", genreRepository.getById(1L)).isPresent() = " + filmRepository.findFilmByTitleAndAndGenre("Harry Potter", genreRepository.getById(1L)).isPresent());

//        Console.main(args);
    }

}



















