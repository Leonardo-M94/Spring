package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/user")
    public String getAll(Model model) {

        List<UserDto> userDtoList = userService.findAll()
                .stream()
                .map(UserDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("users", userDtoList);

        return "users-page";
    }

    @GetMapping("user/add")
    public String addPage(Model model) {

        List<PetDto> petDtoList = petService.findAll()
                .stream()
                .map(PetDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("pets", petDtoList);
        return "add-user-page";
    }

    @PostMapping("user/add")
    public String afterAddPage(EmailDto emailDto, UserDto userDto, PetDto petDto) {

        long id = petService.findByName(petDto.getBreed()).get().getId();
        petDto.setId_pet(String.valueOf(id));

        userDto.setEmailDto(emailDto);
        userDto.setPetDto(petDto);

        userService.insert(UserDto.fromDto(userDto));

        // otladka
        System.out.println(emailService.findAll());

        return "redirect:/user";
    }

    @GetMapping("/user/{id}/edit")
    public String editPage(@PathVariable("id") long id, Model model) {

        UserDto userDto = UserDto.toDto(userService.findById(id));

        userDto.setBirthday(userDto.getBirthday().split(" ")[0]);

        model.addAttribute("userDto", userDto);

        List<PetDto> petDtoList = petService.findAll()
                .stream()
                .map(PetDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("pets", petDtoList);
        return "edit-user-page";
    }

    @PostMapping("/user/edit")
    public String afterEditPage(EmailDto emailDto, PetDto petDto, UserDto userDto) {

        long idPet = petService.findByName(petDto.getBreed()).get().getId();
        petDto.setId_pet(String.valueOf(idPet));

        userDto.setEmailDto(emailDto);
        userDto.setPetDto(petDto);

        userService.update(UserDto.fromDto(userDto));

        return "redirect:/user";
    }

    @GetMapping("/user/{id}/delete")
    public String deletePage(@PathVariable("id") long id, Model model) {
        UserDto userDto = UserDto.toDto(userService.findById(id));
        userDto.setBirthday(userDto.getBirthday().split(" ")[0]);
        model.addAttribute("userDto", userDto);
        return "delete-user-page";
    }

    @PostMapping("/user/delete")
    public String afterDeletePage(UserDto userDto) {

        userService.deleteById(Long.parseLong(userDto.getId_user()));

        // otladka - пользователь удалится вместе с емейлом
        System.out.println(emailService.findAll());

        return "redirect:/user";
    }
}
