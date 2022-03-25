package ru.itsjava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itsjava.domain.Email;

@AllArgsConstructor
@Data
public class EmailDto {

    private String id;
    private String email;
    private String password;

    public static Email fromDto(EmailDto emailDto) {

        if (emailDto.id == null) {
            emailDto.id = "0";
        }

        return new Email(Long.parseLong(emailDto.id), emailDto.email, emailDto.password);
    }

    public static EmailDto toDto(Email email) {

        return new EmailDto(String.valueOf(email.getId()), email.getEmail(), email.getPassword());
    }
}
