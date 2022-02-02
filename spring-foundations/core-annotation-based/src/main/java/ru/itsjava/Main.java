package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("ru.itsjava")    // Указанный пакет сканируется на наличие аннотаций (и конфигурационных классов).
public class Main {
    public static void main(String[] args) {

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Main.class);

        Animal piglet = applicationContext.getBean("piglet", Animal.class);
        piglet.say();

        KindFarmer kindFarmer = applicationContext.getBean("farmer", KindFarmer.class);
        kindFarmer.hi();
    }
}
