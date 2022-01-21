package ru.itsjava.annotations.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class JUnitRunner {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        MyFavoriteTest myFavoriteTest = new MyFavoriteTest();
        Method[] methodsMyFavoriteTest = MyFavoriteTest.class.getDeclaredMethods();

//        System.out.println(Arrays.toString(methodsMyFavoriteTest));

        int passedTests = 0;
        int failedTests = 0;

        for (Method method : methodsMyFavoriteTest) {
            if (method.isAnnotationPresent(Test.class)) {
                System.out.println(method.getName());

                try {
                    method.invoke(myFavoriteTest);
                    System.out.println(method.getName() + " PASSED!");
                    passedTests++;
                } catch (InvocationTargetException exception) {
                    System.out.println(method.getName() + " FAILED!");
                    failedTests++;
                }

            }
        }

        System.out.println("Количество пройденных тестов: " + passedTests);
        System.out.println("Количество упавших тестов: " + failedTests);
    }
}
