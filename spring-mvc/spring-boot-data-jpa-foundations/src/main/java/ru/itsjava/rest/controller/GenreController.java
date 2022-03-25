package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itsjava.rest.dto.GenreDto;
import ru.itsjava.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("genre/{id}")
    public String getPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("genre", GenreDto.toDto(genreService.getGenreById(id)));
        return "get-genre-page";
    }

    @GetMapping("/genres")
    public String getPage(Model model) {

        List<GenreDto> genreDtoList = genreService.getAllGenres()
                .stream()
                .map(genre -> GenreDto.toDto(genre))
                .collect(Collectors.toList());

        model.addAttribute("genres", genreDtoList);

        return "genres-page";
    }

    @GetMapping("genre/add")
    public String addPage() {
        return "add-genre-page";
    }

    @PostMapping("genre/add")
    public String afterAddPage(GenreDto genreDto) {
        genreService.createGenre(GenreDto.fromDto(genreDto));
        return "redirect:/genres";
    }
}


















