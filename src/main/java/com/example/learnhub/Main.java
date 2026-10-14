package com.example.learnhub;

import com.example.learnhub.algorithms.IntervalScheduling;
import com.example.learnhub.algorithms.SlidingWindow;
import com.example.learnhub.algorithms.Sorting;
import com.example.learnhub.algorithms.TwoSum;
import com.example.learnhub.br.BR1Journal;
import com.example.learnhub.br.BR2Registry;
import com.example.learnhub.br.BR3Rating;
import com.example.learnhub.br.BR4Prerequisites;
import com.example.learnhub.br.BR5SemesterPlan;
import com.example.learnhub.br.BR6Analytics;
import com.example.learnhub.br.BR7Trajectories;
import com.example.learnhub.model.Course;
import com.example.learnhub.model.Lesson;
import com.example.learnhub.model.Student;
import com.example.learnhub.util.DataGenerator;
import com.example.learnhub.util.Timer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final BR1Journal HISTORY = new BR1Journal();
    private static final BR2Registry REGISTRY = new BR2Registry();
    private static final BR3Rating RATING = new BR3Rating();
    private static final BR5SemesterPlan SEMESTER_PLAN = new BR5SemesterPlan();
    private static final BR6Analytics ANALYTICS = new BR6Analytics();
    private static final BR7Trajectories TRAJECTORIES = new BR7Trajectories();

    private static final int MENU_BR1 = 1;
    private static final int MENU_BR2 = 2;
    private static final int MENU_BR3 = 3;
    private static final int MENU_BR4 = 4;
    private static final int MENU_BR5 = 5;
    private static final int MENU_BR6 = 6;
    private static final int MENU_BR7 = 7;
    private static final int MENU_END_TO_END = 8;
    private static final int MENU_EXIT = 0;

    public static void main(String[] args) {
        printHeader();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Выберите пункт меню: ");
            switch (choice) {
                case MENU_BR1:
                    demoBR1();
                    break;
                case MENU_BR2:
                    demoBR2();
                    break;
                case MENU_BR3:
                    demoBR3();
                    break;
                case MENU_BR4:
                    demoBR4();
                    break;
                case MENU_BR5:
                    demoBR5();
                    break;
                case MENU_BR6:
                    demoBR6();
                    break;
                case MENU_BR7:
                    demoBR7();
                    break;
                case MENU_END_TO_END:
                    demoEndToEnd();
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
        System.out.println(MENU_BR1 + ". BR-1: история навигации по урокам");
        System.out.println(MENU_BR2 + ". BR-2: реестр студентов");
        System.out.println(MENU_BR3 + ". BR-3: рейтинг и поиск");
        System.out.println(MENU_BR4 + ". BR-4: граф пререквизитов");
        System.out.println(MENU_BR5 + ". BR-5: план семестра и расписание");
        System.out.println(MENU_BR6 + ". BR-6: аналитика активности");
        System.out.println(MENU_BR7 + ". BR-7: траектории обучения");
        System.out.println(MENU_END_TO_END + ". Сквозной сценарий");
        System.out.println(MENU_EXIT + ". Выход");
    }

    private static void demoBR1() {
        System.out.println();
        System.out.println("BR-1: история навигации по урокам");
        HISTORY.clear();

        Lesson lesson1 = new Lesson(1, "Введение в Java");
        Lesson lesson2 = new Lesson(2, "Переменные и типы");
        Lesson lesson3 = new Lesson(3, "Условия и циклы");
        Lesson lesson4 = new Lesson(4, "Массивы");

        System.out.println("Студент открывает уроки по порядку:");
        HISTORY.visitLesson(lesson1);
        System.out.println("  открыт: " + lesson1.getLessonTitle());
        HISTORY.visitLesson(lesson2);
        System.out.println("  открыт: " + lesson2.getLessonTitle());
        HISTORY.visitLesson(lesson3);
        System.out.println("  открыт: " + lesson3.getLessonTitle());
        HISTORY.visitLesson(lesson4);
        System.out.println("  открыт: " + lesson4.getLessonTitle());

        System.out.println("История (от нового к старому): " + HISTORY.snapshot());
        System.out.println("Текущий урок: " + HISTORY.currentLesson());
        System.out.println("Размер истории: " + HISTORY.size());

        System.out.println();
        System.out.println("Нажимаем Назад несколько раз подряд:");
        for (int step = 1; step <= 5; step++) {
            Lesson previous = HISTORY.goBack();
            if (previous == null) {
                System.out.println("  шаг " + step + ": история пуста, назад некуда");
            } else {
                System.out.println("  шаг " + step + ": вернулись к уроку \""
                        + previous.getLessonTitle() + "\", осталось в истории: "
                        + HISTORY.size());
            }
        }
        System.out.println("Итоговый размер истории: " + HISTORY.size());
    }

    private static void demoBR2() {
        System.out.println();
        System.out.println("BR-2: реестр студентов");
        int count = readInt("Сколько студентов сгенерировать? ", 10);
        List<Student> students = DataGenerator.generateStudents(count);
        for (Student student : students) {
            REGISTRY.addStudent(student);
        }
        System.out.println("Добавлено студентов: " + REGISTRY.getStudentCount());
        System.out.println("Высота дерева (ожидается около log2(n)): "
                + REGISTRY.getTreeHeight());
        int studentId = readInt("Введите ID для поиска: ");
        Student found = REGISTRY.findById(studentId);
        System.out.println("Результат поиска: " + found);
        System.out.println("Первые 10 студентов по возрастанию ID:");
        List<Student> sorted = REGISTRY.getAllSortedById();
        int limit = Math.min(10, sorted.size());
        for (int index = 0; index < limit; index++) {
            System.out.println("  " + sorted.get(index));
        }
    }

    private static void demoBR3() {
        System.out.println();
        System.out.println("BR-3: рейтинг и поиск");
        int count = 100_000;
        System.out.println("Генерируем " + count + " студентов...");
        List<Student> students = DataGenerator.generateStudents(count);

        System.out.println("Замеры сортировки на разных объёмах и разных данных:");
        printSortingComparisonTable();

        long rebuildMs = Timer.measureSilent(() -> RATING.rebuild(students));
        System.out.println("Построение рейтинга mergeSort на " + count
                + ": " + rebuildMs + " мс");
        System.out.println("Размер рейтинга: " + RATING.size());

        int low = readInt("Введите нижнюю границу балла: ");
        int high = readInt("Введите верхнюю границу балла: ");
        List<Student> range = RATING.inScoreRange(low, high);
        System.out.println("Найдено студентов в диапазоне: " + range.size());
        int show = Math.min(5, range.size());
        for (int index = 0; index < show; index++) {
            System.out.println("  " + range.get(index));
        }

        int threshold = readInt("Введите порог для поиска первого студента: ");
        Student first = RATING.firstAtLeast(threshold);
        System.out.println("Первый студент с баллом >= " + threshold + ": " + first);

        if (!students.isEmpty()) {
            Student sample = students.get(0);
            System.out.println("Место студента " + sample + " в рейтинге: "
                    + RATING.placeOf(sample));
        }
    }

    private static void printSortingComparisonTable() {
        int[] sizes = {100, 1_000, 10_000, 100_000};
        System.out.println("  объём    тип данных           insertionSort   mergeSort       adaptiveSort");

        for (int size : sizes) {
            List<Integer> randomData = generateRandomList(size);
            List<Integer> sortedData = generateSortedList(size);

            long insertionRandomMs = Timer.measureSilent(() ->
                    Sorting.insertionSort(new ArrayList<>(randomData), Integer::compare));
            long mergeRandomMs = Timer.measureSilent(() ->
                    Sorting.mergeSort(new ArrayList<>(randomData), Integer::compare));
            long adaptiveRandomMs = Timer.measureSilent(() ->
                    Sorting.adaptiveSort(new ArrayList<>(randomData), Integer::compare));

            System.out.println("  " + size + "\tслучайные\t\t" + insertionRandomMs
                    + " мс\t\t" + mergeRandomMs + " мс\t\t" + adaptiveRandomMs + " мс");

            long insertionSortedMs = Timer.measureSilent(() ->
                    Sorting.insertionSort(new ArrayList<>(sortedData), Integer::compare));
            long mergeSortedMs = Timer.measureSilent(() ->
                    Sorting.mergeSort(new ArrayList<>(sortedData), Integer::compare));
            long adaptiveSortedMs = Timer.measureSilent(() ->
                    Sorting.adaptiveSort(new ArrayList<>(sortedData), Integer::compare));

            System.out.println("  " + size + "\tотсортированные\t\t" + insertionSortedMs
                    + " мс\t\t" + mergeSortedMs + " мс\t\t" + adaptiveSortedMs + " мс");
        }
    }

    private static List<Integer> generateRandomList(int size) {
        Random random = new Random(42);
        List<Integer> data = new ArrayList<>(size);
        for (int index = 0; index < size; index++) {
            data.add(random.nextInt(1_000_000));
        }
        return data;
    }

    private static List<Integer> generateSortedList(int size) {
        List<Integer> data = new ArrayList<>(size);
        for (int index = 0; index < size; index++) {
            data.add(index);
        }
        return data;
    }

    private static void demoBR4() {
        System.out.println();
        System.out.println("BR-4: граф пререквизитов");
        BR4Prerequisites demo = new BR4Prerequisites();

        System.out.println("Демонстрационный граф:");
        demo.addPrerequisite(1, 2, 10);
        demo.addPrerequisite(1, 3, 20);
        demo.addPrerequisite(2, 4, 5);
        demo.addPrerequisite(3, 4, 5);
        demo.addPrerequisite(4, 5, 7);
        demo.addCourse(6);
        demo.addPrerequisite(6, 7, 3);
        System.out.println("  курсы 1-5 связаны, 6-7 — отдельный трек");

        System.out.println("BFS от курса 1: " + demo.reachableFrom(1));
        System.out.println("Число независимых треков: " + demo.countIndependentTracks());
        System.out.println("Циклы: " + demo.findCycle());

        System.out.println("Кратчайшие трудоёмкости от курса 1:");
        Map<Integer, Integer> distances = demo.shortestHoursFrom(1);
        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            int courseId = entry.getKey();
            int hours = entry.getValue();
            if (hours == Integer.MAX_VALUE) {
                System.out.println("  курс " + courseId + ": недостижим");
            } else {
                System.out.println("  курс " + courseId + ": " + hours + " ч.");
            }
        }

        System.out.println();
        System.out.println("Ручной ввод дополнительных курсов и пререквизитов:");
        int extraCourses = readInt("Сколько курсов добавить вручную? ", 0);
        for (int index = 0; index < extraCourses; index++) {
            int courseId = readInt("  ID курса #" + (index + 1) + ": ");
            demo.addCourse(courseId);
            System.out.println("  добавлен курс " + courseId);
        }

        int extraEdges = readInt("Сколько пререквизитов добавить? ", 0);
        for (int index = 0; index < extraEdges; index++) {
            int fromCourseId = readInt("  От курса: ");
            int toCourseId = readInt("  К курсу: ");
            int hours = readInt("  Трудоёмкость (часы): ");
            demo.addPrerequisite(fromCourseId, toCourseId, hours);
            System.out.println("  добавлен пререквизит " + fromCourseId
                    + " -> " + toCourseId + " (" + hours + " ч.)");
        }

        if (extraCourses > 0 || extraEdges > 0) {
            System.out.println("После ручного ввода:");
            System.out.println("  число независимых треков: "
                    + demo.countIndependentTracks());
            System.out.println("  BFS от курса 1: " + demo.reachableFrom(1));
            System.out.println("  циклы: " + demo.findCycle());
        }

        System.out.println();
        System.out.println("Граф с гарантированным циклом (отдельный экземпляр):");
        BR4Prerequisites cyclic = new BR4Prerequisites();
        cyclic.addPrerequisite(10, 11, 1);
        cyclic.addPrerequisite(11, 12, 1);
        cyclic.addPrerequisite(12, 10, 1);
        System.out.println("  найденный цикл: " + cyclic.findCycle());
    }

    private static void demoBR5() {
        System.out.println();
        System.out.println("BR-5: план семестра и расписание");
        System.out.println();
        System.out.println("Почему две задачи решаются по-разному:");
        System.out.println("  Рюкзак (часы, польза) — NP-полная задача. Жадный даёт неоптимум.");
        System.out.println("    Пример: 3 курса (10ч/60), (20ч/100), (30ч/120), лимит 50ч.");
        System.out.println("    Жадный берёт 10/60 -> 20/100, польза 160.");
        System.out.println("    Оптимум — 20/100 + 30/120 = 220. Нужно ДП.");
        System.out.println("  Расписание лекций — интервальная задача. Жадный по времени");
        System.out.println("    окончания оптимален (доказано). ДП не нужно.");
        System.out.println();

        List<Course> courses = List.of(
                new Course(1, "Java Basics", 30, 50),
                new Course(2, "Алгоритмы", 50, 90),
                new Course(3, "Базы данных", 25, 40),
                new Course(4, "Веб-разработка", 40, 60),
                new Course(5, "Машинное обучение", 60, 95)
        );
        int maxHours = 90;
        System.out.println("Максимум часов: " + maxHours);

        List<Course> optimalPlan = SEMESTER_PLAN.buildOptimalPlan(courses, maxHours);
        List<Course> greedyPlan = SEMESTER_PLAN.buildFastPlan(courses, maxHours);

        System.out.println("Оптимальный план (ДП):");
        System.out.println("  курсы: " + optimalPlan);
        System.out.println("  польза: " + SEMESTER_PLAN.totalBenefit(optimalPlan));
        System.out.println("  часы: " + SEMESTER_PLAN.totalHours(optimalPlan));
        System.out.println("Быстрый план (жадный):");
        System.out.println("  курсы: " + greedyPlan);
        System.out.println("  польза: " + SEMESTER_PLAN.totalBenefit(greedyPlan));
        System.out.println("  часы: " + SEMESTER_PLAN.totalHours(greedyPlan));

        long optimalMs = Timer.measureSilent(() ->
                SEMESTER_PLAN.buildOptimalPlan(courses, maxHours));
        long greedyMs = Timer.measureSilent(() ->
                SEMESTER_PLAN.buildFastPlan(courses, maxHours));
        Timer.printComparison("план семестра", optimalMs, greedyMs);

        System.out.println();
        System.out.println("Расписание лекций в одной аудитории:");
        List<IntervalScheduling.Interval> intervals = List.of(
                new IntervalScheduling.Interval(9, 10, "Лекция A"),
                new IntervalScheduling.Interval(9, 12, "Лекция B"),
                new IntervalScheduling.Interval(10, 11, "Лекция C"),
                new IntervalScheduling.Interval(11, 13, "Лекция D"),
                new IntervalScheduling.Interval(13, 15, "Лекция E")
        );
        List<IntervalScheduling.Interval> selected = SEMESTER_PLAN.buildLectureSchedule(intervals);
        System.out.println("Выбрано занятий (жадный по времени окончания): " + selected.size());
        for (IntervalScheduling.Interval interval : selected) {
            System.out.println("  " + interval);
        }
    }

    private static void demoBR6() {
        System.out.println();
        System.out.println("BR-6: аналитика активности");
        int days = 100_000;
        int windowSize = 7;
        List<Integer> activity = DataGenerator.generateDailyActivity(days, 1000);

        SlidingWindow.WindowResult fastWindow = ANALYTICS.busiestWindow(activity, windowSize);
        SlidingWindow.WindowResult naiveWindow = ANALYTICS.busiestWindowNaive(activity, windowSize);
        long fastMs = Timer.measureSilent(() -> ANALYTICS.busiestWindow(activity, windowSize));
        long naiveMs = Timer.measureSilent(() -> ANALYTICS.busiestWindowNaive(activity, windowSize));
        System.out.println("Скользящее окно: " + fastWindow);
        System.out.println("Наивный результат: " + naiveWindow);
        System.out.println("Суммы совпадают: " + (fastWindow.getSum() == naiveWindow.getSum()));
        Timer.printComparison("окно", fastMs, naiveMs);

        System.out.println();
        System.out.println("Поиск пары с заданной суммой (точное совпадение):");
        List<Integer> sample = List.of(2, 7, 11, 15);
        int sampleTarget = 9;
        TwoSum.Pair samplePair = ANALYTICS.findPairExact(sample, sampleTarget);
        System.out.println("  массив " + sample + ", цель " + sampleTarget + ": " + samplePair);

        System.out.println();
        System.out.println("Поиск ближайшей пары на большом массиве:");
        List<Integer> scores = DataGenerator.generateScores(10_000, 5000);
        int target = 7000;

        TwoSum.Pair closestFast = ANALYTICS.findPairClosest(scores, target);
        TwoSum.Pair closestNaive = ANALYTICS.findPairClosestNaive(scores, target);

        long closestFastMs = Timer.measureSilent(() -> ANALYTICS.findPairClosest(scores, target));
        long closestNaiveMs = Timer.measureSilent(() -> ANALYTICS.findPairClosestNaive(scores, target));

        System.out.println("  быстрый (mergeSort + два указателя): " + closestFast);
        System.out.println("  наивный (перебор): " + closestNaive);
        int fastSum = closestFast.getFirstValue() + closestFast.getSecondValue();
        int naiveSum = closestNaive.getFirstValue() + closestNaive.getSecondValue();
        System.out.println("  суммы совпадают: " + (fastSum == naiveSum));
        Timer.printComparison("ближайшая пара", closestFastMs, closestNaiveMs);
    }

    private static void demoBR7() {
        System.out.println();
        System.out.println("BR-7: траектории обучения");
        System.out.println("За семестр можно пройти один или два модуля.");
        System.out.println("Формула: f(n) = f(n-1) + f(n-2), f(0)=f(1)=1.");
        System.out.println();
        System.out.println("Проверка формулы: f(2)=2, f(3)=3, f(4)=5, f(5)=8.");
        System.out.println();
        System.out.println("N    наивно              оптимизировано");
        for (int n = 10; n <= 30; n += 5) {
            BigInteger naive = TRAJECTORIES.countNaive(n);
            BigInteger optimized = TRAJECTORIES.countOptimized(n);
            System.out.println(n + "    " + naive + "    " + optimized);
        }

        int smallN = 25;
        System.out.println();
        System.out.println("Замер на N = " + smallN + ":");
        long naiveMs = Timer.measureSilent(() -> TRAJECTORIES.countNaive(smallN));
        long optimizedMs = Timer.measureSilent(() -> TRAJECTORIES.countOptimized(smallN));
        Timer.printComparison("траектории N = " + smallN, naiveMs, optimizedMs);

        int largeN = 80;
        System.out.println();
        System.out.println("Оптимизированный расчёт для N = " + largeN + ":");
        BigInteger largeResult = TRAJECTORIES.countOptimized(largeN);
        System.out.println("  результат: " + largeResult);
        long largeMs = Timer.measureSilent(() -> TRAJECTORIES.countOptimized(largeN));
        System.out.println("  время: " + largeMs + " мс");
        System.out.println("Наивный расчёт для N = " + largeN + " не запускается:");
        System.out.println("  сложность O(2^" + largeN + ") делает его недопустимо медленным.");
    }

    private static void demoEndToEnd() {
        System.out.println();
        System.out.println("Сквозной сценарий");
        System.out.println("Данные передаются между модулями по цепочке.");
        System.out.println();

        BR2Registry registry = new BR2Registry();
        BR3Rating rating = new BR3Rating();
        BR4Prerequisites prerequisites = new BR4Prerequisites();
        BR5SemesterPlan semesterPlan = new BR5SemesterPlan();
        BR1Journal history = new BR1Journal();

        System.out.println("Шаг 1. Регистрируем студентов (BR-2)");
        Student ivan = new Student(101, "Иван", 500);
        Student maria = new Student(102, "Мария", 700);
        Student peter = new Student(103, "Пётр", 300);
        registry.addStudent(ivan);
        registry.addStudent(maria);
        registry.addStudent(peter);
        System.out.println("  зарегистрировано: " + registry.getStudentCount());
        System.out.println("  высота дерева: " + registry.getTreeHeight());

        System.out.println();
        System.out.println("Шаг 2. Строим рейтинг из того же реестра (BR-3)");
        List<Student> registeredStudents = registry.getAllSortedById();
        rating.rebuild(registeredStudents);
        System.out.println("  размер рейтинга: " + rating.size());
        System.out.println("  место Ивана: " + rating.placeOf(ivan));
        System.out.println("  место Марии: " + rating.placeOf(maria));
        System.out.println("  первый с баллом >= 600: " + rating.firstAtLeast(600));

        System.out.println();
        System.out.println("Шаг 3. Добавляем курсы и пререквизиты (BR-4)");
        prerequisites.addPrerequisite(1, 2, 20);
        prerequisites.addPrerequisite(2, 3, 30);
        prerequisites.addPrerequisite(1, 4, 15);
        System.out.println("  доступно после курса 1: "
                + prerequisites.reachableFrom(1));
        System.out.println("  минимальная трудоёмкость от 1: "
                + prerequisites.shortestHoursFrom(1));

        System.out.println();
        System.out.println("Шаг 4. Составляем план семестра из курсов, доступных по BR-4 (BR-5)");
        List<Course> availableCourses = List.of(
                new Course(2, "Продвинутая Java", 30, 60),
                new Course(3, "Проектирование систем", 40, 80),
                new Course(4, "Фронтенд", 25, 45)
        );
        List<Course> plan = semesterPlan.buildOptimalPlan(availableCourses, 60);
        System.out.println("  план: " + plan);
        System.out.println("  польза: " + semesterPlan.totalBenefit(plan));
        System.out.println("  часы: " + semesterPlan.totalHours(plan));

        System.out.println();
        System.out.println("Шаг 5. Аналитика активности студента (BR-6)");
        List<Integer> activity = DataGenerator.generateDailyActivity(30, 100);
        SlidingWindow.WindowResult busiest = ANALYTICS.busiestWindow(activity, 7);
        System.out.println("  самая активная неделя: " + busiest);

        System.out.println();
        System.out.println("Шаг 6. Число оставшихся траекторий для 10 модулей (BR-7)");
        BigInteger trajectories = TRAJECTORIES.countOptimized(10);
        System.out.println("  траекторий прохождения программы: " + trajectories);

        System.out.println();
        System.out.println("Шаг 7. История навигации: студент открыл уроки (BR-1)");
        Lesson lesson1 = new Lesson(1, "Введение в Java");
        Lesson lesson2 = new Lesson(2, "Алгоритмы сортировки");
        history.visitLesson(lesson1);
        history.visitLesson(lesson2);
        System.out.println("  история: " + history.snapshot());
        System.out.println("  текущий урок: " + history.currentLesson());

        System.out.println();
        System.out.println("Шаг 8. Кнопка Назад: возврат к предыдущему уроку (BR-1)");
        Lesson previous = history.goBack();
        System.out.println("  вернулись к: " + previous);
        System.out.println("  осталось в истории: " + history.size());

        System.out.println();
        System.out.println("Сценарий завершён.");
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = SCANNER.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException exception) {
                System.out.println("Некорректный ввод. Введите целое число.");
            }
        }
    }

    private static int readInt(String prompt, int defaultValue) {
        System.out.print(prompt);
        String line = SCANNER.nextLine().trim();
        if (line.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException exception) {
            System.out.println("Некорректный ввод. Использую значение по умолчанию "
                    + defaultValue + ".");
            return defaultValue;
        }
    }
}