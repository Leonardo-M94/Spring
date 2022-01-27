package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

//        Animal piglet = new Piglet();
//        KindFarmer kindFarmer = new KindFarmer(piglet);
//        kindFarmer.hi();

        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("context.xml");

//        Animal piglet = applicationContext.getBean("pig", Animal.class);
//        piglet.say();

        KindFarmer kindFarmer = applicationContext.getBean("farmer", KindFarmer.class);
        kindFarmer.hi();
    }
}
