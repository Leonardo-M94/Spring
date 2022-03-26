package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itsjava.domain.Genre;
import ru.itsjava.rest.dto.GenreDto;
import ru.itsjava.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping({"genre/{id}"})
    public String getPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("genre", GenreDto.toDto(this.genreService.getGenreById(id)));
        return "get-genre-page";
    }

    @GetMapping({"/genre"})
    public String getPage(Model model) {

        List<GenreDto> genreDtoList = genreService.getAllGenres()
                .stream()
                .map(GenreDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("genres", genreDtoList);
        return "genres-page";
    }

    @GetMapping({"genre/add"})
    public String addPage() {
        return "add-genre-page";
    }

    @PostMapping({"genre/add"})
    public String afterAddPage(GenreDto genreDto) {
        this.genreService.createGenre(GenreDto.fromDto(genreDto));
        return "redirect:/genre";
    }

    @GetMapping("genre/{id}/edit")
    public String editPage(@PathVariable("id") long id, Model model) {
        Genre genreById = genreService.getGenreById(id);
        model.addAttribute("genreDto", GenreDto.toDto(genreById));
        return "edit-genre-page";
    }

    @PostMapping("genre/{id}/edit")
    public String afterEditPage(GenreDto genreDto) {
        genreService.updateGenre(GenreDto.fromDto(genreDto));
        return "redirect:/genre";
    }

    @GetMapping("genre/{id}/delete")
    public String deletePage(@PathVariable("id") long id, Model model) {
        Genre genreById = genreService.getGenreById(id);
        model.addAttribute("genreDto", GenreDto.toDto(genreById));
        return "delete-genre-page";
    }

    @PostMapping("genre/{id}/delete")
    public String afterDeletePage(GenreDto genreDto) {
        genreService.deleteGenre(GenreDto.fromDto(genreDto));
        return "redirect:/genre";
    }




















}