package ru.itsjava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itsjava.domain.Pet;

@AllArgsConstructor
@Data
public class PetDto {
    private String id_pet;
    private String breed;

    public static Pet fromDto(PetDto petDto) {
        if (petDto.id_pet == null) {
            petDto.id_pet = "0";
        }
        return new Pet(Long.parseLong(petDto.id_pet), petDto.breed);
    }

    public static PetDto toDto(Pet pet) {
        return new PetDto(String.valueOf(pet.getId()), pet.getBreed());
    }

}
