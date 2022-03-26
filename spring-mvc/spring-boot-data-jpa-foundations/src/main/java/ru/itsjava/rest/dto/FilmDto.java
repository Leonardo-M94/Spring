package ru.itsjava.rest.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itsjava.domain.Film;
import ru.itsjava.domain.Genre;
import ru.itsjava.domain.Place;

@AllArgsConstructor
@Data
public class FilmDto {
    private String id;
    private String title;
    private String genre;
    private String places;

    public static Film fromDto(FilmDto filmDto) {
        if (filmDto.id == null) {
            filmDto.id = "0";
        }

        long id = Long.parseLong(filmDto.id);
        Genre genre = new Genre(0L, filmDto.genre);

        List<Place> places = Stream.of(filmDto.places.split(","))
                .map(place -> new Place(0L, place, id))
                .collect(Collectors.toList());

        return new Film(id, filmDto.title, genre, places);
    }

    public static FilmDto toDto(Film film) {
        String id = String.valueOf(film.getId());
        String title = film.getTitle();
        String genre = film.getGenre().getName();

        String places = film.getPlaces()
                .stream()
                .map(place -> place.getName())
                .collect(Collectors.joining(", "));

        return new FilmDto(id, title, genre, places);
    }
}
