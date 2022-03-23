package ru.itsjava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itsjava.domain.Pet;

@AllArgsConstructor
@Data
public class PetDto {

    private String id;
    private String breed;

    public static Pet fromDto(PetDto petDto) {

        return new Pet(Long.parseLong(petDto.id), petDto.breed);
    }

    public static PetDto toDto(Pet pet) {

        return new PetDto(String.valueOf(pet.getId()), pet.getBreed());
    }

}
