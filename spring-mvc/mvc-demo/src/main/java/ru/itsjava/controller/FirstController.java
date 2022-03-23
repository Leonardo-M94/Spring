package ru.itsjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/") // На любой запрос
    public String getForAll() {
        return "index";
    }
}
