package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itsjava.domain.Email;
import ru.itsjava.domain.User;
import ru.itsjava.service.EmailService;
import ru.itsjava.service.PetService;
import ru.itsjava.service.UserService;

@SpringBootApplication
public class SpringBootJpaHwApplication {

    public static void main(String[] args) {

        var context = SpringApplication.run(SpringBootJpaHwApplication.class, args);

        UserService userService = context.getBean(UserService.class);
        System.out.println(userService.findAll());
        User user = userService.findById(1L);
        System.out.println(user);

        EmailService emailService = context.getBean(EmailService.class);
        Email email = new Email(0L, "login@ya.ru", "*********");
        emailService.insert(email);

        email.setPassword("qwerty123");
        emailService.update(email);
        System.out.println(emailService.findByLogin(email.getEmail()).get());

        PetService petService = context.getBean(PetService.class);
        System.out.println(petService.findAll());
    }

}
