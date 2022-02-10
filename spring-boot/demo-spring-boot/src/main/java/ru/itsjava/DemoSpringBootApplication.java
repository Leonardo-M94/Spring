package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itsjava.services.IService;

@SpringBootApplication
public class DemoSpringBootApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                SpringApplication.run(DemoSpringBootApplication.class, args);
        System.out.println(context.getBean(IService.class));
    }

}
