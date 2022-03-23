package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itsjava.rest.dto.UserDto;
import ru.itsjava.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String getAll(Model model) {

        List<UserDto> userDtoList = userService.findAll()
                .stream()
                .map(user -> UserDto.toDto(user))
                .collect(Collectors.toList());

        model.addAttribute("users", userDtoList);

        return "users-page";
    }
}
