package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.domain.Email;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;
import ru.itsjava.repository.EmailRepository;
import ru.itsjava.repository.PetRepository;
import ru.itsjava.repository.UserRepository;

import java.sql.Date;

@SpringBootApplication
public class SpringBootJpaHwApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringBootJpaHwApplication.class, args);
        UserRepository userRepository = context.getBean(UserRepository.class);
        EmailRepository emailRepository = context.getBean(EmailRepository.class);
        PetRepository petRepository = context.getBean(PetRepository.class);

        User user = userRepository.getById(1L);
        System.out.println(user);

        Pet pet = petRepository.getById(5L);

        user.setPet(pet);
        System.out.println(user);

        emailRepository.save(new Email(0L, "qwerty123", "**********"));
        Email email = emailRepository.getById(6L);
        System.out.println(email);

        User insertedUser = new User(0L, "Genry Roberts", Date.valueOf("1989-10-15"), true, email, pet);
        userRepository.save(insertedUser);
        System.out.println("userRepository.getById(5L) = " + userRepository.getById(5L));

        System.out.println("emailRepository.getByEmail(\"Kovaleva.Anna.Vas@gmail.com\").isPresent() = " + emailRepository.getByEmail("Kovaleva.Anna.Vas@gmail.com").isPresent());

        System.out.println("userRepository.getByEmail(emailRepository.getById(3L)).isPresent() = " + userRepository.getByEmail(emailRepository.getById(3L)).isPresent());

    }

}
