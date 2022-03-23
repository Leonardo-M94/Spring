package ru.itsjava.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itsjava.domain.Notebook;
import ru.itsjava.service.NotebookService;

@Controller
@RequiredArgsConstructor
public class NotebookController {

    private final NotebookService notebookService;

    @GetMapping("/notebook/{id}")
    public String getPage(@PathVariable("id") long id, Model model) { // через перменную в url: http://localhost:8080/notebook/1
        Notebook notebook = notebookService.findById(id);
        model.addAttribute("notebook", notebook);
        System.out.println(notebook);

        return "get-page";
    }
}


















