package ru.itsjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("ru.itsjava")
public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Main.class);

        KindFarmer farmer1 = applicationContext.getBean(KindFarmer.class);
//        KindFarmer farmer2 = applicationContext.getBean(KindFarmer.class);
//
//        System.out.println(farmer1);
//        System.out.println(farmer2);
//        System.out.println("(farmer1 == farmer2) = " + (farmer1 == farmer2));

        applicationContext.close();
    }
}
