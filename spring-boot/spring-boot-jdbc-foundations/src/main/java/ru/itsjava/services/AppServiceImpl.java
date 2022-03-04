package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.FacultyDao;
import ru.itsjava.domain.Faculty;
import ru.itsjava.domain.Student;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AppServiceImpl implements AppService {

    private final StudentService studentService;
    private final IOService ioService;
    private final FacultyService facultyService;

    @Override
    public void start() {
        System.out.println("Добро пожаловать в наш Университет!");

        while (true) {
            System.out.println("Введите номер меню");
            System.out.println("1 - напечатать всех студентов, 2 - добавить студента, остальное выход");
            int menuNum = ioService.inputInt();

            if (menuNum == 1) {
                printAllStudents();
            } else if (menuNum == 2) {
                insertStudent();
            } else {
                System.exit(0);
            }
        }
    }

    private Faculty chooseFaculty() {
        Optional<Faculty> studentFaculty = Optional.empty();

        while (studentFaculty.isEmpty()) {
            System.out.println("Введите факультет: ");
            String faculty = ioService.input();

            studentFaculty = facultyService.findByName(faculty);

            if (!studentFaculty.isPresent()) {
                System.out.println(faculty + " - такого факультета не существует.");
                System.out.println("Существующие факультеты:");

                for (Faculty presentFaculty : facultyService.findAll()) {
                    System.out.println(presentFaculty.getName());
                }
            }
        }

        return studentFaculty.get();
    }

    @Override
    public void insertStudent() {
        System.out.println("Введите студента");
        System.out.println("Введите ФИО: ");
        String fio = ioService.input();

        System.out.println("Введите возраст: ");
        int age = ioService.inputInt();

        Faculty studentFaculty = chooseFaculty();

        Student student = new Student(fio, age, studentFaculty);
        studentService.insert(student);
    }

    @Override
    public void printAllStudents() {
        List<Student> studentList = studentService.findAll();

        System.out.println("Список студентов: " + studentList);
    }
}




















