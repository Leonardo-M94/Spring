package ru.itsjava.annotations.junit;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.itsjava.annotations.junit.my_annotations.After;
import ru.itsjava.annotations.junit.my_annotations.AfterAll;
import ru.itsjava.annotations.junit.my_annotations.Before;
import ru.itsjava.annotations.junit.my_annotations.DisplayName;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MyJUnit {
    private final Object objTestClass;
    private int passedTests = 0;
    private int failedTests = 0;
    private final List<Method> beforeAllMethods = new ArrayList<>();
    private final List<Method> beforeTestMethods = new ArrayList<>();
    private final List<Method> testMethods = new ArrayList<>();
    private final List<Method> afterTestMethods = new ArrayList<>();
    private final List<Method> afterAllMethods = new ArrayList<>();

    @SneakyThrows
    public void start() {
        fillAllMethodsGroup();

        for (Method method : beforeAllMethods) {
            method.invoke(objTestClass);
        }

        for (Method method : testMethods) {

            for (Method beforeTestMethod : beforeTestMethods) {
                beforeTestMethod.invoke(objTestClass);
            }

            try {
                System.out.println(method.getAnnotation(DisplayName.class).testName());
                method.invoke(objTestClass);
                System.out.println(method.getName() + " PASSED!");
                passedTests++;
            } catch (InvocationTargetException exception) {
                System.out.println(method.getName() + " FAILED!");
                failedTests++;
            } finally {
                for (Method afterTestMethod : afterTestMethods) {
                    afterTestMethod.invoke(objTestClass);
                }
            }
        }

        for (Method afterAllTestMethod : afterAllMethods) {
            afterAllTestMethod.invoke(objTestClass);
        }

        printResults();
    }

    private void fillAllMethodsGroup() {
        for (Method method : objTestClass.getClass().getDeclaredMethods()) {

            if (method.isAnnotationPresent(BeforeAll.class)) {
                beforeAllMethods.add(method);
                continue;
            }

            if (method.isAnnotationPresent(Before.class)) {
                beforeTestMethods.add(method);
                continue;
            }

            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
                continue;
            }

            if (method.isAnnotationPresent(After.class)) {
                afterTestMethods.add(method);
                continue;
            }

            if (method.isAnnotationPresent(AfterAll.class)) {
                afterAllMethods.add(method);
                continue;
            }
        }
    }

    private void printResults() {
        System.out.println("=============================================");
        System.out.println("Количество пройденных тестов: " + passedTests);
        System.out.println("Количество упавших тестов: " + failedTests);
        System.out.println("=============================================");
    }
}
