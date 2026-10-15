package com.example.learnhub.demonstrations;

import com.example.learnhub.algorithms.IntervalScheduling;
import com.example.learnhub.model.Course;
import com.example.learnhub.services.SemesterPlanningService;
import com.example.learnhub.utils.Timer;

import java.util.List;

public class SemesterPlanningDemonstration {
    private static final int MAXIMUM_HOURS = 90;

    public void run() {
        System.out.println();
        System.out.println("BR-5: план семестра и расписание");
        System.out.println();
        System.out.println("Почему две задачи решаются по-разному:");
        System.out.println("Рюкзак (часы, польза) - NP-полная задача. Жадный даёт неоптимум.");
        System.out.println("Пример: 3 курса (10ч/60), (20ч/100), (30ч/120), лимит 50ч.");
        System.out.println("Жадный берёт 10/60 -> 20/100, польза 160.");
        System.out.println("Оптимум - 20/100 + 30/120 = 220. Нужно ДП.");
        System.out.println("Расписание лекций - интервальная задача. Жадный по времени");
        System.out.println("окончания оптимален (доказано). ДП не нужно.");
        System.out.println();

        SemesterPlanningService semesterPlanningService = new SemesterPlanningService();

        List<Course> courses = List.of(
                new Course(1, "Java Basics", 30, 50),
                new Course(2, "Алгоритмы", 50, 90),
                new Course(3, "Базы данных", 25, 40),
                new Course(4, "Веб-разработка", 40, 60),
                new Course(5, "Машинное обучение", 60, 95)
        );
        System.out.println("Максимум часов: " + MAXIMUM_HOURS);

        List<Course> optimalPlan = semesterPlanningService.buildOptimalPlan(
                courses, MAXIMUM_HOURS);
        List<Course> greedyPlan = semesterPlanningService.buildFastPlan(
                courses, MAXIMUM_HOURS);

        System.out.println("Оптимальный план (ДП):");
        System.out.println("курсы: " + optimalPlan);
        System.out.println("польза: " + semesterPlanningService.totalBenefit(optimalPlan));
        System.out.println("часы: " + semesterPlanningService.totalHours(optimalPlan));
        System.out.println("Быстрый план (жадный):");
        System.out.println("курсы: " + greedyPlan);
        System.out.println("польза: " + semesterPlanningService.totalBenefit(greedyPlan));
        System.out.println("часы: " + semesterPlanningService.totalHours(greedyPlan));

        long optimalMilliseconds = Timer.measureSilent(() ->
                semesterPlanningService.buildOptimalPlan(courses, MAXIMUM_HOURS));
        long greedyMilliseconds = Timer.measureSilent(() ->
                semesterPlanningService.buildFastPlan(courses, MAXIMUM_HOURS));
        Timer.printComparison("план семестра",
                "динамическое программирование", optimalMilliseconds,
                "жадный алгоритм", greedyMilliseconds);

        System.out.println();
        System.out.println("Расписание лекций в одной аудитории:");
        List<IntervalScheduling.Interval> intervals = List.of(
                new IntervalScheduling.Interval(9, 10, "Лекция A"),
                new IntervalScheduling.Interval(9, 12, "Лекция B"),
                new IntervalScheduling.Interval(10, 11, "Лекция C"),
                new IntervalScheduling.Interval(11, 13, "Лекция D"),
                new IntervalScheduling.Interval(13, 15, "Лекция E")
        );
        List<IntervalScheduling.Interval> selectedIntervals =
                semesterPlanningService.buildLectureSchedule(intervals);
        System.out.println("Выбрано занятий (жадный по времени окончания): "
                + selectedIntervals.size());
        for (IntervalScheduling.Interval interval : selectedIntervals) {
            System.out.println("  " + interval);
        }
    }
}