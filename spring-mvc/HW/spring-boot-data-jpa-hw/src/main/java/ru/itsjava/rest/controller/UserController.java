package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itsjava.domain.User;
import ru.itsjava.rest.dto.EmailDto;
import ru.itsjava.rest.dto.PetDto;
import ru.itsjava.rest.dto.UserDto;
import ru.itsjava.service.EmailService;
import ru.itsjava.service.PetService;
import ru.itsjava.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PetService petService;
    private final EmailService emailService;

    @GetMapping("/users")
    public String getAll(Model model) {

        List<UserDto> userDtoList = userService.findAll()
                .stream()
                .map(user -> UserDto.toDto(user))
                .collect(Collectors.toList());

        model.addAttribute("users", userDtoList);

        return "users-page";
    }

    @GetMapping("user/add")
    public String addPage(Model model) {

        List<PetDto> petDtoList = petService.findAll()
                .stream()
                .map(pet -> PetDto.toDto(pet))
                .collect(Collectors.toList());

        model.addAttribute("pets", petDtoList);
        return "add-user-page";
    }

    @PostMapping("user/add")
    public String afterAddPage(EmailDto emailDto, UserDto userDto, PetDto petDto) {

        long id = petService.findByName(petDto.getBreed()).get().getId();
        petDto.setId(String.valueOf(id));

        userDto.setEmailDto(emailDto);
        userDto.setPetDto(petDto);

        userService.insert(UserDto.fromDto(userDto));

        // otladka
        System.out.println(emailService.findAll());

        return "redirect:/users";
    }

}
