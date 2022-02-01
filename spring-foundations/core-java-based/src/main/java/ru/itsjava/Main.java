package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("ru.itsjava.configuration")  // Указываем расположение конфигурационного класса,
public class Main {                         // в котором прописаны бины и др. возможные аннотации.
    public static void main(String[] args) {

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Main.class);

//        Animal piglet = applicationContext.getBean("myBestPiglet", Animal.class);
//        Animal piglet = applicationContext.getBean("piglet", Animal.class);
//        piglet.say();

        KindFarmer kindFarmer = applicationContext.getBean("farmer", KindFarmer.class);
        kindFarmer.hi();
    }
}
