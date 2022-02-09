package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.itsjava.domain.Film;
import ru.itsjava.services.FilmService;

@EnableAspectJAutoProxy // Подключаем режим АОП
@ComponentScan("ru.itsjava")
public class Application {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        Film film = applicationContext.getBean(FilmService.class).getFilm();
        System.out.println("film = " + film);
    }
}
