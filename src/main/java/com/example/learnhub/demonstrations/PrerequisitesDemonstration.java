package com.example.learnhub.demonstrations;

import com.example.learnhub.services.PrerequisiteService;
import com.example.learnhub.utils.InputValidator;

import java.util.Map;
import java.util.Scanner;

public class PrerequisitesDemonstration {
    private final Scanner scanner;

    public PrerequisitesDemonstration(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.println();
        System.out.println("BR-4: граф пререквизитов");
        PrerequisiteService prerequisiteService = new PrerequisiteService();

        System.out.println("Демонстрационный граф:");
        prerequisiteService.addPrerequisite(1, 2, 10);
        prerequisiteService.addPrerequisite(1, 3, 20);
        prerequisiteService.addPrerequisite(2, 4, 5);
        prerequisiteService.addPrerequisite(3, 4, 5);
        prerequisiteService.addPrerequisite(4, 5, 7);
        prerequisiteService.addCourse(6);
        prerequisiteService.addPrerequisite(6, 7, 3);
        System.out.println("курсы 1-5 связаны, 6-7 - отдельный трек");

        System.out.println("BFS от курса 1: " + prerequisiteService.reachableFrom(1));
        System.out.println("Число независимых треков: " + prerequisiteService.countIndependentTracks());
        System.out.println("Циклы: " + prerequisiteService.findCycle());

        System.out.println("Кратчайшие трудоёмкости от курса 1:");
        Map<Integer, Integer> distances = prerequisiteService.shortestHoursFrom(1);
        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            int courseId = entry.getKey();
            int hours = entry.getValue();
            if (hours == Integer.MAX_VALUE) {
                System.out.println("курс " + courseId + ": недостижим");
            }

            else {
                System.out.println("курс " + courseId + ": " + hours + " ч.");
            }
        }

        System.out.println();
        System.out.println("Ручной ввод дополнительных курсов и пререквизитов:");
        readAdditionalData(prerequisiteService);

        System.out.println();
        System.out.println("Граф с гарантированным циклом (отдельный экземпляр):");
        PrerequisiteService cyclicService = new PrerequisiteService();
        cyclicService.addPrerequisite(10, 11, 1);
        cyclicService.addPrerequisite(11, 12, 1);
        cyclicService.addPrerequisite(12, 10, 1);
        System.out.println("найденный цикл: " + cyclicService.findCycle());
    }

    private void readAdditionalData(PrerequisiteService prerequisiteService) {
        int extraCourses = InputValidator.readInt(scanner, "Сколько курсов добавить вручную? ", 0);
        for (int index = 0; index < extraCourses; index++) {
            int courseId = InputValidator.readInt(scanner, "ID курса #" + (index + 1) + ": ");
            prerequisiteService.addCourse(courseId);
            System.out.println("добавлен курс " + courseId);
        }

        int extraEdges = InputValidator.readInt(scanner, "Сколько пререквизитов добавить? ", 0);
        for (int index = 0; index < extraEdges; index++) {
            int fromCourseId = InputValidator.readInt(scanner, "От курса: ");
            int toCourseId = InputValidator.readInt(scanner, "К курсу: ");
            int hours = InputValidator.readInt(scanner, "Трудоёмкость (часы): ");
            prerequisiteService.addPrerequisite(fromCourseId, toCourseId, hours);
            System.out.println("добавлен пререквизит " + fromCourseId + " -> " + toCourseId + " (" + hours + " ч.)");
        }

        if (extraCourses > 0 || extraEdges > 0) {
            System.out.println("После ручного ввода:");
            System.out.println("число независимых треков: "
                    + prerequisiteService.countIndependentTracks());
            System.out.println("BFS от курса 1: "
                    + prerequisiteService.reachableFrom(1));
            System.out.println("циклы: " + prerequisiteService.findCycle());
        }
    }
}