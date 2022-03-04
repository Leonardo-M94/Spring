package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.domain.Email;
import ru.itsjava.domain.User;
import ru.itsjava.domain.Pet;
import ru.itsjava.services.AppService;
import ru.itsjava.services.UserService;

import java.sql.Date;
import java.sql.SQLException;

@SpringBootApplication
public class SpringBootJdbcHwApplication {

    public static void main(String[] args) throws SQLException {

        ApplicationContext context = SpringApplication.run(SpringBootJdbcHwApplication.class, args);
//        context.getBean(UserService.class).insert(
//                new User("Barzheev Semen Semenovich", Date.valueOf("1990-11-10"), true,
//                        new Email(5L, "Test-qwerty123@mail.ru", "jGhF409BLki"),
//                        new Pet(1L, "Мейкун"))
//        );
        context.getBean(AppService.class).start();

        Console.main(args);
    }

}
