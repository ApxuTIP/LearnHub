package com.example.learnhub.demonstrations;

import com.example.learnhub.model.Lesson;
import com.example.learnhub.services.NavigationHistoryService;

public class NavigationHistoryDemonstration {
    private static final int BACK_STEP_COUNT = 5;

    public void run() {
        System.out.println();
        System.out.println("BR-1: история навигации по урокам");
        NavigationHistoryService historyService = new NavigationHistoryService();
        Lesson[] lessons = {
                new Lesson(1, "Введение в Java"),
                new Lesson(2, "Переменные и типы"),
                new Lesson(3, "Условия и циклы"),
                new Lesson(4, "Массивы")
        };

        System.out.println("Студент открывает уроки по порядку:");
        for (Lesson lesson : lessons) {
            historyService.visitLesson(lesson);
            System.out.println("  открыт: " + lesson.getLessonTitle());
        }

        System.out.println("История (от нового к старому): " + historyService.snapshot());
        System.out.println("Текущий урок: " + historyService.currentLesson());
        System.out.println("Размер истории: " + historyService.size());

        System.out.println();
        System.out.println("Нажимаем Назад несколько раз подряд:");
        for (int step = 1; step <= BACK_STEP_COUNT; step++) {
            Lesson previousLesson = historyService.goBack();
            if (previousLesson == null) {
                System.out.println(String.format("  шаг %d: история пуста, назад некуда", step));
            } else {
                System.out.println(String.format("  шаг %d: вернулись к уроку \"%s\", осталось в истории: %d",
                        step, previousLesson.getLessonTitle(), historyService.size()));
            }
        }
        System.out.println("Итоговый размер истории: " + historyService.size());
    }
}
