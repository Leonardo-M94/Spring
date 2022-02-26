package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.dao.EmailDao;
import ru.itsjava.domain.Email;

import java.sql.Date;
import java.sql.SQLException;

@SpringBootApplication
public class SpringBootJdbcHwApplication {

    public static void main(String[] args) throws SQLException {

        ApplicationContext context = SpringApplication.run(SpringBootJdbcHwApplication.class, args);
        EmailDao emailDao = context.getBean(EmailDao.class);

        System.out.println("emailDao.count() = " + emailDao.count());

        Email testEmail = new Email("Test-email.123@ya.ru", "***********",
                "O.K.", Date.valueOf("1990-11-04"), true);

        emailDao.insert(testEmail);

        System.out.println("emailDao.count() = " + emailDao.count());

        emailDao.updatePassword(5L, "QWERTY-123");

        Email updatedEmail = new Email("UPDATED-EMAIL@ya.ru", "GOOD PASSWORD", "Who I am",
                Date.valueOf("2000-01-01"), true);

        updatedEmail.setId(1L);

        emailDao.update(updatedEmail);

        System.out.println("emailDao.findById(1L) = " + emailDao.findById(1L));

        emailDao.deleteById(updatedEmail.getId());

        emailDao.deleteByEmail(testEmail.getEmail());

        System.out.println("emailDao.count() = " + emailDao.count());

        Console.main(args);
    }

}
