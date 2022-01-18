package ru.itsjava.annotations.homework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, InstantiationException {
        MathStudent mathStudent = new MathStudent("Александр", "Сидоров", (short) 2);
        System.out.println("Before: " + mathStudent);

        Class<? extends MathStudent> mathStudentClass = mathStudent.getClass();
        Field surname = mathStudentClass.getDeclaredField("surname");
        surname.setAccessible(true);
        surname.set(mathStudent, "Иванов");
        University universityAnnotation = mathStudentClass.getAnnotation(University.class);
        System.out.println("After: " + mathStudent + " " + universityAnnotation.department());

        Class<?> biologyStudentClass = Class.forName("ru.itsjava.annotations.homework.BiologyStudent");
        BiologyStudent biologyStudent = (BiologyStudent) biologyStudentClass.getConstructors()[0]
                .newInstance("Максим", "Ложкарев", (short) 3);
        System.out.println(biologyStudent + " "
                + biologyStudentClass.getAnnotation(University.class).department());
    }
}
