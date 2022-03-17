package ru.itsjava;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itsjava.service.FilmService;
import ru.itsjava.service.GenreService;

@SpringBootApplication
public class SpringBootJpaFoundationsApplication {

    @SneakyThrows
    public static void main(String[] args) {
        var context = SpringApplication.run(SpringBootJpaFoundationsApplication.class, args);
        FilmService filmService = context.getBean(FilmService.class);
        filmService.printAllFilms();

        GenreService genreService = context.getBean(GenreService.class);
        genreService.changeGenre("fantasy", "comedy");
        genreService.printGenre("comedy");

    }

}



















