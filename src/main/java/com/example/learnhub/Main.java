package com.example.learnhub;

import com.example.learnhub.demonstrations.ActivityAnalyticsDemonstration;
import com.example.learnhub.demonstrations.EndToEndDemonstration;
import com.example.learnhub.demonstrations.NavigationHistoryDemonstration;
import com.example.learnhub.demonstrations.PrerequisitesDemonstration;
import com.example.learnhub.demonstrations.RatingDemonstration;
import com.example.learnhub.demonstrations.SemesterPlanningDemonstration;
import com.example.learnhub.demonstrations.StudentRegistryDemonstration;
import com.example.learnhub.demonstrations.TrajectoriesDemonstration;
import com.example.learnhub.utils.InputValidator;

import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int MENU_NAVIGATION = 1;
    private static final int MENU_REGISTRY = 2;
    private static final int MENU_RATING = 3;
    private static final int MENU_PREREQUISITES = 4;
    private static final int MENU_SEMESTER_PLANNING = 5;
    private static final int MENU_ANALYTICS = 6;
    private static final int MENU_TRAJECTORIES = 7;
    private static final int MENU_END_TO_END = 8;
    private static final int MENU_EXIT = 0;

    private Main() {
    }

    public static void main(String[] args) {
        printHeader();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = InputValidator.readInt(SCANNER, "Выберите пункт меню: ");
            switch (choice) {
                case MENU_NAVIGATION:
                    new NavigationHistoryDemonstration().run();
                    break;
                case MENU_REGISTRY:
                    new StudentRegistryDemonstration(SCANNER).run();
                    break;
                case MENU_RATING:
                    new RatingDemonstration(SCANNER).run();
                    break;
                case MENU_PREREQUISITES:
                    new PrerequisitesDemonstration(SCANNER).run();
                    break;
                case MENU_SEMESTER_PLANNING:
                    new SemesterPlanningDemonstration().run();
                    break;
                case MENU_ANALYTICS:
                    new ActivityAnalyticsDemonstration().run();
                    break;
                case MENU_TRAJECTORIES:
                    new TrajectoriesDemonstration().run();
                    break;
                case MENU_END_TO_END:
                    new EndToEndDemonstration().run();
                    break;
                case MENU_EXIT:
                    running = false;
                    break;
                default:
                    System.out.println("Неизвестный пункт меню. Попробуйте снова.");
                    break;
            }
        }
        System.out.println("Работа завершена.");
    }

    private static void printHeader() {
        System.out.println("LearnHub - платформа онлайн курсов");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Меню");
        System.out.println(MENU_NAVIGATION + ". История навигации по урокам");
        System.out.println(MENU_REGISTRY + ". Реестр студентов");
        System.out.println(MENU_RATING + ". Рейтинг и поиск");
        System.out.println(MENU_PREREQUISITES + ". Граф пререквизитов");
        System.out.println(MENU_SEMESTER_PLANNING + ". План семестра и расписание");
        System.out.println(MENU_ANALYTICS + ". Аналитика активности");
        System.out.println(MENU_TRAJECTORIES + ". Траектории обучения");
        System.out.println(MENU_END_TO_END + ". Сквозной сценарий");
        System.out.println(MENU_EXIT + ". Выход");
    }
}