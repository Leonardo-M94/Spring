package ru.itsjava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itsjava.domain.Email;

@AllArgsConstructor
@Data
public class EmailDto {
    private String id_email;
    private String email;
    private String password;

    public static Email fromDto(EmailDto emailDto) {
        if (emailDto.id_email == null) {
            emailDto.id_email = "0";
        }
        return new Email(Long.parseLong(emailDto.id_email), emailDto.email, emailDto.password);
    }

    public static EmailDto toDto(Email email) {
        return new EmailDto(String.valueOf(email.getId()), email.getEmail(), email.getPassword());
    }
}
