package ru.itsjava.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.itsjava.Animal;
import ru.itsjava.Lamb;
import ru.itsjava.Piglet;

@Import(OtherConfig.class)      // Импорт бинов из другого класса.
@Configuration                  // Аннотация указывает, что данный класс является конфигурационным.
public class MyConfig {

    // @Bean("myBestPiglet")    // В ковычках переопределяем id (имя) бина.
    @Bean                       // Аннотация указывает на создание бина типа Animal.
    public Animal piglet() {
        return new Piglet();
    }

    @Bean
    public Animal lamb() {
        return new Lamb();
    }
}
