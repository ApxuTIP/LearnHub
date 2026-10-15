package com.example.learnhub.demonstrations;

import com.example.learnhub.model.Student;
import com.example.learnhub.services.StudentRegistryService;
import com.example.learnhub.utils.DataGenerator;
import com.example.learnhub.utils.InputValidator;

import java.util.List;
import java.util.Scanner;

public class StudentRegistryDemonstration {

    private static final int DEFAULT_STUDENT_COUNT = 10;
    private static final int SHOW_LIMIT = 10;

    private final Scanner scanner;

    public StudentRegistryDemonstration(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.println();
        System.out.println("BR-2: реестр студентов");

        StudentRegistryService registryService = new StudentRegistryService();

        int studentCount = InputValidator.readPositiveInt(scanner,
                "Сколько студентов сгенерировать? ", DEFAULT_STUDENT_COUNT);
        List<Student> students = DataGenerator.generateStudents(studentCount);
        for (Student student : students) {
            registryService.addStudent(student);
        }

        System.out.println("Добавлено студентов: " + registryService.getStudentCount());
        System.out.println("Высота дерева (ожидается около log2(n)): "
                + registryService.getTreeHeight());

        int studentId = InputValidator.readInt(scanner, "Введите ID для поиска: ");
        Student found = registryService.findById(studentId);
        System.out.println("Результат поиска: " + found);

        System.out.println("Первые " + SHOW_LIMIT + " студентов по возрастанию ID:");
        List<Student> sortedStudents = registryService.getAllSortedById();
        int limit = Math.min(SHOW_LIMIT, sortedStudents.size());
        for (int index = 0; index < limit; index++) {
            System.out.println("  " + sortedStudents.get(index));
        }
    }
}