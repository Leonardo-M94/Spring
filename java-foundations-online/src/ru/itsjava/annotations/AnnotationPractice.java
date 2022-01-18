package ru.itsjava.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class AnnotationPractice {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
//        Person ivan = new Person("Vanya", true);
//        System.out.println(ivan);
//
//        Class<? extends Person> ivanClass = ivan.getClass();
//
//        System.out.println(ivanClass.getName());
//        System.out.println(ivanClass.getSimpleName());

        Class<?> personClass = Class.forName("ru.itsjava.annotations.Person");
        System.out.println(personClass.getName());
        //Object o = personClass.newInstance();
        Person vanya = (Person) personClass.getConstructor(String.class, Boolean.TYPE)
                .newInstance("Vanya", true);
        System.out.println(vanya);

        System.out.println(personClass.getAnnotations()[0]);

        Field isGood = personClass.getDeclaredField("isGood");
        isGood.setAccessible(true);
        isGood.setBoolean(vanya, false);
        System.out.println(vanya);

    }
}
