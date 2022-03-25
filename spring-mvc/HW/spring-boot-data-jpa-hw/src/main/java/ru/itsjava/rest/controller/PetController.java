package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itsjava.rest.dto.PetDto;
import ru.itsjava.service.PetService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping("/pets")
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
        return "redirect:/pets";
    }
}
