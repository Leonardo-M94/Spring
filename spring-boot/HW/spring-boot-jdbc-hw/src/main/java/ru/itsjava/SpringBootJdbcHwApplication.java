package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.domain.Email;
import ru.itsjava.domain.Pet;
import ru.itsjava.services.EmailService;

import java.sql.Date;
import java.sql.SQLException;

@SpringBootApplication
public class SpringBootJdbcHwApplication {

    public static void main(String[] args) throws SQLException {

        ApplicationContext context = SpringApplication.run(SpringBootJdbcHwApplication.class, args);
        context.getBean(EmailService.class).insert(
                new Email("newEmail-90@mail.ru", "r4sdfsUIN09", "Test Testovich",
                        Date.valueOf("1990-11-10"), true, new Pet(1L, "Мейкун"))
        );

        Console.main(args);
    }

}
