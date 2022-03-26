package ru.itsjava.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itsjava.rest.dto.FilmDto;
import ru.itsjava.service.FilmService;

@Controller
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping({"/film"})
    public String allPage(Model model) {
        List<FilmDto> filmDtoList = filmService.getAllFilms()
                .stream()
                .map(film -> FilmDto.toDto(film))
                .collect(Collectors.toList());
        model.addAttribute("films", filmDtoList);
        return "films-page";
    }
}
