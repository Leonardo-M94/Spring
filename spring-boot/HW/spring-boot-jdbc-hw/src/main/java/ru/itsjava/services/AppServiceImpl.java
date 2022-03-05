package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.domain.Email;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;
import ru.itsjava.exceptions.AlreadyExistEmailException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AppServiceImpl implements AppService {

    private final UserService userService;
    private final EmailService emailService;
    private final PetService petService;
    private final IOService ioService;

    @Override
    public void start() {
        System.out.println("Welcome");

        while (true) {
            System.out.println("Input menu number.");
            System.out.println("1 - Print all users,\n2 - Add user,\nelse - Exit");

            switch (ioService.inputInt()) {
                case 1:
                    printAllUsers();
                    break;
                case 2:
                    insertUser();
                    break;
                default:
                    System.out.println("App exit");
                    return;
            }
        }
    }

    private Pet choosePet() {   // Проверяем наличие breed в БД, добавляем в БД новую запись
        Optional<Pet> optionalPet = Optional.empty();

        while (optionalPet.isEmpty()) {
            System.out.println("Input your pet breed: ");
            String breed = ioService.input();

            optionalPet = petService.findByName(breed);

            if (!optionalPet.isPresent()) {
                System.out.println("The entry does not exist in the DB.");
                System.out.println("1 - Download a list of existing breed,\n2 - Add new entry,\nelse - Cancel");

                switch (ioService.inputInt()) {
                    case 1:
                        for (Pet pet : petService.findAll()) {
                            System.out.println("\t- " + pet.getBreed());
                        }
                        break;
                    case 2:
                        petService.insert(new Pet(breed));
                        optionalPet = petService.findByName(breed);
                        break;
                    default:
                        System.exit(0);
                }
            }
        }
        return optionalPet.get();
    }

    private Email insertEmail() { // Проверяем уникальность логина при insert
        while (true) {
            try {
                System.out.println("Input email as your login: ");
                String email = ioService.input();

                System.out.println("Set a password: ");
                String password = ioService.input();

                emailService.insert(new Email(email, password));

                return emailService.findByLogin(email).get();

            } catch (AlreadyExistEmailException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    @Override
    public void insertUser() {
        Email email = insertEmail();

        System.out.println("Input user FIO: ");
        String fio = ioService.input();

        System.out.println("Input user birthday in format yyyy-mm-dd: ");
        Date birthday = Date.valueOf(ioService.input());

        Boolean male = false;
        System.out.println("Input user male in format m/w: ");
        if (ioService.input().equals("m")) {
            male = true;
        } else {
            male = false;
        }

        Pet pet = choosePet();

        User newUser = new User(fio, birthday, male, email, pet);

        userService.insert(newUser);
    }

    @Override
    public void printAllUsers() {
        List<User> usersList = userService.findAll();

        System.out.println("User list: ");
        for (User user : usersList) {
            System.out.println(user);
        }
    }
}
