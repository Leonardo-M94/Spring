package ru.itsjava.annotations.junit;

import ru.itsjava.annotations.junit.my_annotations.After;
import ru.itsjava.annotations.junit.my_annotations.AfterAll;
import ru.itsjava.annotations.junit.my_annotations.Before;
import ru.itsjava.annotations.junit.my_annotations.DisplayName;

public class MyFavoriteTest {

    @BeforeAll
    public void beforeAllMethod() {
        System.out.println("BEFORE ALL\n");
    }

    @Before
    public void beforeTest() {
        System.out.println("--- * START TEST: * ---");
    }

    @Test
    @DisplayName(testName = "Тест №1")
    public void test1() {
    }

    @Test
    @DisplayName(testName = "Тест №2")
    public void test2() {
    }

    @Test
    @DisplayName(testName = "Тест №3")
    public void test3() {
        throw new RuntimeException();
    }

    @Test
    @DisplayName(testName = "Тест №4")
    public void test4() {
        throw new RuntimeException();
    }

    @After
    public void afterTest() {
        System.out.println("--- FINISH TEST ---\n");
    }

    @AfterAll
    public void afterAllTests() {
        System.out.println("ALL TESTS ARE COMPLETED");
    }

    public void noTestMethod() {
    }
}
