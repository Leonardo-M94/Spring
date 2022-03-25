package ru.itsjava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itsjava.domain.User;

import java.sql.Date;

@AllArgsConstructor
@Data
public class UserDto {

    private String id;
    private String fio;
    private String birthday;
    private String male;
    private EmailDto emailDto;
    private PetDto petDto;

    public static User fromDto(UserDto userDto) {

        if (userDto.id == null) {
            userDto.id = "0";
        }

        return new User(
                Long.parseLong(userDto.id),
                userDto.fio,
                Date.valueOf(userDto.birthday),
                Boolean.valueOf(userDto.male),
                EmailDto.fromDto(userDto.emailDto),
                PetDto.fromDto(userDto.petDto));
    }

    public static UserDto toDto(User user) {

        return new UserDto(
                String.valueOf(user.getId()),
                user.getFio(),
                String.valueOf(user.getBirthday()),
                String.valueOf(user.getMale()),
                EmailDto.toDto(user.getEmail()),
                PetDto.toDto(user.getPet()));
    }
}
