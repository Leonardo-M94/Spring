package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itsjava.rest.dto.FilmDto;
import ru.itsjava.service.FilmService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping("/films")
    public String allPage(Model model) {

        List<FilmDto> filmDtoList = filmService.getAllFilms()
                .stream()
                .map(film -> FilmDto.toDto(film))
                .collect(Collectors.toList());

        model.addAttribute("films", filmDtoList);
        return "films-page";
    }
}
























