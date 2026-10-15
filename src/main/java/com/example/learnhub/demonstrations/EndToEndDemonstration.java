package com.example.learnhub.demonstrations;

import com.example.learnhub.algorithms.SlidingWindow;
import com.example.learnhub.model.Course;
import com.example.learnhub.model.Lesson;
import com.example.learnhub.model.Student;
import com.example.learnhub.services.ActivityAnalyticsService;
import com.example.learnhub.services.NavigationHistoryService;
import com.example.learnhub.services.PrerequisiteService;
import com.example.learnhub.services.RatingService;
import com.example.learnhub.services.SemesterPlanningService;
import com.example.learnhub.services.StudentRegistryService;
import com.example.learnhub.services.TrajectoryService;
import com.example.learnhub.utils.DataGenerator;

import java.math.BigInteger;
import java.util.List;

public class EndToEndDemonstration {
    private static final int ACTIVITY_DAYS = 30;
    private static final int ACTIVITY_BOUND = 100;
    private static final int WINDOW_SIZE = 7;
    private static final int TRAJECTORY_MODULES = 10;

    public void run() {
        System.out.println();
        System.out.println("Сквозной сценарий");
        System.out.println("Данные передаются между модулями по цепочке.");
        System.out.println();

        StudentRegistryService registryService = new StudentRegistryService();
        RatingService ratingService = new RatingService();
        PrerequisiteService prerequisiteService = new PrerequisiteService();
        SemesterPlanningService semesterPlanningService = new SemesterPlanningService();
        NavigationHistoryService navigationHistoryService = new NavigationHistoryService();
        ActivityAnalyticsService analyticsService = new ActivityAnalyticsService();
        TrajectoryService trajectoryService = new TrajectoryService();

        System.out.println("Шаг 1. Регистрируем студентов (BR-2)");
        Student ivan = new Student(101, "Иван", 500);
        Student maria = new Student(102, "Мария", 700);
        Student peter = new Student(103, "Пётр", 300);
        registryService.addStudent(ivan);
        registryService.addStudent(maria);
        registryService.addStudent(peter);
        System.out.println("зарегистрировано: " + registryService.getStudentCount());
        System.out.println("высота дерева: " + registryService.getTreeHeight());

        System.out.println();
        System.out.println("Шаг 2. Строим рейтинг из того же реестра (BR-3)");
        List<Student> registeredStudents = registryService.getAllSortedById();
        ratingService.rebuild(registeredStudents);
        System.out.println("размер рейтинга: " + ratingService.size());
        System.out.println("место Ивана: " + ratingService.placeOf(ivan));
        System.out.println("место Марии: " + ratingService.placeOf(maria));
        System.out.println("первый с баллом >= 600: " + ratingService.firstAtLeast(600));

        System.out.println();
        System.out.println("Шаг 3. Добавляем курсы и пререквизиты (BR-4)");
        prerequisiteService.addPrerequisite(1, 2, 20);
        prerequisiteService.addPrerequisite(2, 3, 30);
        prerequisiteService.addPrerequisite(1, 4, 15);
        System.out.println("доступно после курса 1: " + prerequisiteService.reachableFrom(1));
        System.out.println("минимальная трудоёмкость от 1: " + prerequisiteService.shortestHoursFrom(1));

        System.out.println();
        System.out.println("Шаг 4. Составляем план семестра из курсов, доступных по BR-4 (BR-5)");
        List<Course> availableCourses = List.of(
                new Course(2, "Продвинутая Java", 30, 60),
                new Course(3, "Проектирование систем", 40, 80),
                new Course(4, "Фронтенд", 25, 45)
        );
        List<Course> plan = semesterPlanningService.buildOptimalPlan(availableCourses, 60);
        System.out.println("план: " + plan);
        System.out.println("польза: " + semesterPlanningService.totalBenefit(plan));
        System.out.println("часы: " + semesterPlanningService.totalHours(plan));

        System.out.println();
        System.out.println("Шаг 5. Аналитика активности студента (BR-6)");
        List<Integer> activity = DataGenerator.generateDailyActivity(ACTIVITY_DAYS, ACTIVITY_BOUND);
        SlidingWindow.WindowResult busiest = analyticsService.busiestWindow(activity, WINDOW_SIZE);
        System.out.println("самая активная неделя: " + busiest);

        System.out.println();
        System.out.println("Шаг 6. Число оставшихся траекторий для 10 модулей (BR-7)");
        BigInteger trajectoryCount = trajectoryService.countOptimized(TRAJECTORY_MODULES);
        System.out.println("траекторий прохождения программы: " + trajectoryCount);

        System.out.println();
        System.out.println("Шаг 7. История навигации: студент открыл уроки (BR-1)");
        Lesson lesson1 = new Lesson(1, "Введение в Java");
        Lesson lesson2 = new Lesson(2, "Алгоритмы сортировки");
        navigationHistoryService.visitLesson(lesson1);
        navigationHistoryService.visitLesson(lesson2);
        System.out.println("история: " + navigationHistoryService.snapshot());
        System.out.println("текущий урок: " + navigationHistoryService.currentLesson());

        System.out.println();
        System.out.println("Шаг 8. Кнопка Назад: возврат к предыдущему уроку (BR-1)");
        Lesson previousLesson = navigationHistoryService.goBack();
        System.out.println("вернулись к: " + previousLesson);
        System.out.println("осталось в истории: " + navigationHistoryService.size());

        System.out.println();
        System.out.println("Сценарий завершён.");
    }
}