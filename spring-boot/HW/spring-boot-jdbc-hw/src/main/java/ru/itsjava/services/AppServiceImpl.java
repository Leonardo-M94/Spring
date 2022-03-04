package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.PetDao;
import ru.itsjava.dao.UserDao;
import ru.itsjava.domain.Email;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AppServiceImpl implements AppService {

    private final UserService userService;
    private final EmailService emailService;
    private final IOService ioService;
    private final PetDao petDao;

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
                    System.exit(0);
            }
        }
    }

    @Override
    public void insertUser() {
        System.out.println("Input email as your login: ");
        String email = ioService.input();

        System.out.println("Set a password: ");
        String password = ioService.input();

        Email newEmail = new Email(email, password);

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

        System.out.println("Input your pet breed: ");
        Pet pet = petDao.findByName(ioService.input());

        long emailId = emailService.insert(newEmail);
        newEmail.setId(emailId);

        User newUser = new User(fio, birthday, male, newEmail, pet);

        userService.insert(newUser);
    }

    @Override
    public void printAllUsers() {
        List<User> usersList = userService.findAll();

        System.out.println("User list: ");
        for(User user: usersList) {
            System.out.println(user);
        }
        System.out.println("");
    }
}
