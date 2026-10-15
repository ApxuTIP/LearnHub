package com.example.learnhub.demonstrations;

import com.example.learnhub.model.Lesson;
import com.example.learnhub.services.NavigationHistoryService;

public class NavigationHistoryDemonstration {
    private static final int BACK_STEP_COUNT = 5;

    public void run() {
        System.out.println();
        System.out.println("BR-1: история навигации по урокам");
        NavigationHistoryService historyService = new NavigationHistoryService();
        Lesson lesson1 = new Lesson(1, "Введение в Java");
        Lesson lesson2 = new Lesson(2, "Переменные и типы");
        Lesson lesson3 = new Lesson(3, "Условия и циклы");
        Lesson lesson4 = new Lesson(4, "Массивы");

        System.out.println("Студент открывает уроки по порядку:");
        historyService.visitLesson(lesson1);
        System.out.println("открыт: " + lesson1.getLessonTitle());
        historyService.visitLesson(lesson2);
        System.out.println("открыт: " + lesson2.getLessonTitle());
        historyService.visitLesson(lesson3);
        System.out.println("открыт: " + lesson3.getLessonTitle());
        historyService.visitLesson(lesson4);
        System.out.println("открыт: " + lesson4.getLessonTitle());

        System.out.println("История (от нового к старому): " + historyService.snapshot());
        System.out.println("Текущий урок: " + historyService.currentLesson());
        System.out.println("Размер истории: " + historyService.size());

        System.out.println();
        System.out.println("Нажимаем Назад несколько раз подряд:");
        for (int step = 1; step <= BACK_STEP_COUNT; step++) {
            Lesson previousLesson = historyService.goBack();
            if (previousLesson == null) {
                System.out.println("шаг " + step + ": история пуста, назад некуда");
            }

            else {
                System.out.println("шаг " + step + ": вернулись к уроку \""
                        + previousLesson.getLessonTitle() + "\", осталось в истории: "
                        + historyService.size());
            }
        }
        System.out.println("Итоговый размер истории: " + historyService.size());
    }
}