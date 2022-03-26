package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itsjava.rest.dto.PetDto;
import ru.itsjava.service.PetService;
import ru.itsjava.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping("/pet")
    public String getAll(Model model) {

        List<PetDto> petDtoList = petService.findAll()
                .stream()
                .map(pet -> PetDto.toDto(pet))
                .collect(Collectors.toList());

        model.addAttribute("pets", petDtoList);

        return "pets-page";
    }

    @GetMapping("pet/add")
    public String addPage() {
        return "add-pet-page";
    }

    @PostMapping("pet/add")
    public String afterAddPage(PetDto petDto) {
        petService.insert(PetDto.fromDto(petDto));
        return "redirect:/pet";
    }

    @GetMapping("/pet/{id}/edit")
    public String editPage(@PathVariable("id") long id, Model model) {

        model.addAttribute("pet", PetDto.toDto(petService.findById(id)));
        return "edit-pet-page";
    }

    @PostMapping("/pet/{id}/edit")
    public String afterEditPage(PetDto petDto) {

        petService.update(PetDto.fromDto(petDto));
        return "redirect:/pet";
    }

    @GetMapping("/pet/{id}/delete")
    public String deletePage(@PathVariable("id") long id, Model model) {

        model.addAttribute("pet", PetDto.toDto(petService.findById(id)));
        return "delete-pet-page";
    }

    @PostMapping("/pet/{id}/delete")
    public String afterDeletePage(PetDto petDto) {

        petService.delete(PetDto.fromDto(petDto));
        return "redirect:/pet";
    }
}
