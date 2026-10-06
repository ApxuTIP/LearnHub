package com.example.learnhub;

import com.example.learnhub.algorithms.IntervalScheduling;
import com.example.learnhub.algorithms.SlidingWindow;
import com.example.learnhub.algorithms.TwoSum;
import com.example.learnhub.br.BR1Journal;
import com.example.learnhub.br.BR2Registry;
import com.example.learnhub.br.BR3Rating;
import com.example.learnhub.br.BR4Prerequisites;
import com.example.learnhub.br.BR5SemesterPlan;
import com.example.learnhub.br.BR6Analytics;
import com.example.learnhub.br.BR7Trajectories;
import com.example.learnhub.model.Action;
import com.example.learnhub.model.Course;
import com.example.learnhub.model.Student;
import com.example.learnhub.util.DataGenerator;
import com.example.learnhub.util.Timer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final BR1Journal JOURNAL = new BR1Journal();
    private static final BR2Registry REGISTRY = new BR2Registry();
    private static final BR3Rating RATING = new BR3Rating();
    private static final BR5SemesterPlan SEMESTER_PLAN = new BR5SemesterPlan();
    private static final BR6Analytics ANALYTICS = new BR6Analytics();
    private static final BR7Trajectories TRAJECTORIES = new BR7Trajectories();

    public static void main(String[] args) {
        printHeader();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Выберите пункт меню: ");
            switch (choice) {
                case 1 -> demoBR1();
                case 2 -> demoBR2();
                case 3 -> demoBR3();
                case 4 -> demoBR4();
                case 5 -> demoBR5();
                case 6 -> demoBR6();
                case 7 -> demoBR7();
                case 8 -> demoEndToEnd();
                case 0 -> running = false;
                default -> System.out.println("Неизвестный пункт меню. Попробуйте снова.");
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
        System.out.println("1. BR-1: журнал действий с откатом");
        System.out.println("2. BR-2: реестр студентов");
        System.out.println("3. BR-3: рейтинг и поиск");
        System.out.println("4. BR-4: граф пререквизитов");
        System.out.println("5. BR-5: план семестра и расписание");
        System.out.println("6. BR-6: аналитика активности");
        System.out.println("7. BR-7: траектории обучения");
        System.out.println("8. Сквозной сценарий");
        System.out.println("0. Выход");
    }


    private static void demoBR1() {
        System.out.println();
        System.out.println("BR-1: журнал действий с откатом");
        JOURNAL.clear();
        JOURNAL.record(new Action("Добавлен студент Иван", new Runnable() {
            @Override
            public void run() {
                System.out.println("    откат: удалён Иван");
            }
        }));
        JOURNAL.record(new Action("Начислены баллы", new Runnable() {
            @Override
            public void run() {
                System.out.println("    откат: баллы сняты");
            }
        }));
        JOURNAL.record(new Action("Записан на курс Java", new Runnable() {
            @Override
            public void run() {
                System.out.println("    откат: снят с курса");
            }
        }));
        System.out.println("Журнал: " + JOURNAL.snapshot());
        System.out.println("Размер: " + JOURNAL.size());
        System.out.println("Откатываем последнее действие...");
        String description = JOURNAL.undoLast();
        System.out.println("Откачено: " + description);
        System.out.println("Осталось в журнале: " + JOURNAL.size());
        System.out.println("Проверим пустой журнал:");
        JOURNAL.clear();
        String emptyResult = JOURNAL.undoLast();
        System.out.println("Результат отката пустого журнала: " + emptyResult);
    }

    private static void demoBR2() {
        System.out.println();
        System.out.println("BR-2: реестр студентов");
        int count = readInt("Сколько студентов сгенерировать? ", 10);
        List<Student> students = DataGenerator.generateStudents(count);
        for (Student student : students) {
            REGISTRY.add(student);
        }
        System.out.println("Добавлено студентов: " + REGISTRY.size());
        System.out.println("Высота дерева: " + REGISTRY.treeHeight());
        int id = readInt("Введите ID для поиска: ");
        Student found = REGISTRY.find(id);
        System.out.println("Результат поиска: " + found);
        System.out.println("Первые 10 студентов по возрастанию ID:");
        List<Student> sorted = REGISTRY.allSorted();
        int limit = Math.min(10, sorted.size());
        for (int i = 0; i < limit; i++) {
            System.out.println("  " + sorted.get(i));
        }
    }

    private static void demoBR3() {
        System.out.println();
        System.out.println("BR-3: рейтинг и поиск");
        final int count = 100_000;
        System.out.println("Генерируем " + count + " студентов...");
        final List<Student> students = DataGenerator.generateStudents(count);

        Timer.measure("построение рейтинга", new Runnable() {
            @Override
            public void run() {
                RATING.rebuild(students);
            }
        });

        System.out.println("Проверим на маленькой выборке:");
        List<Student> small = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            small.add(students.get(i));
        }
        final List<Student> smallStudents = small;
        final BR3Rating smallRating = new BR3Rating();
        Timer.measure("рейтинг на 20 студентах", new Runnable() {
            @Override
            public void run() {
                smallRating.rebuild(smallStudents);
            }
        });
        System.out.println("Сравнение размеров: " + RATING.size()
                + " и " + smallRating.size());

        int low = readInt("Введите нижнюю границу балла: ");
        int high = readInt("Введите верхнюю границу балла: ");
        List<Student> range = RATING.inScoreRange(low, high);
        System.out.println("Найдено студентов в диапазоне: " + range.size());
        int show = Math.min(5, range.size());
        for (int i = 0; i < show; i++) {
            System.out.println("  " + range.get(i));
        }

        int threshold = readInt("Введите порог для поиска первого студента: ");
        Student first = RATING.firstAtLeast(threshold);
        System.out.println("Первый студент с баллом >= " + threshold + ": " + first);
    }

    private static void demoBR4() {
        System.out.println();
        System.out.println("BR-4: граф пререквизитов");
        BR4Prerequisites demo = new BR4Prerequisites();
        demo.addPrerequisite(1, 2, 10);
        demo.addPrerequisite(1, 3, 20);
        demo.addPrerequisite(2, 4, 5);
        demo.addPrerequisite(3, 4, 5);
        demo.addPrerequisite(4, 5, 7);
        demo.addCourse(6);
        demo.addPrerequisite(6, 7, 3);

        System.out.println("BFS от курса 1: " + demo.reachableFrom(1));
        System.out.println("Число независимых треков: " + demo.countIndependentTracks());
        System.out.println("Циклы: " + demo.findCycle());
        Map<Integer, Integer> distances = demo.shortestHoursFrom(1);
        System.out.println("Кратчайшие трудоёмкости от курса 1:");
        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            System.out.println("  курс " + entry.getKey() + ": " + entry.getValue());
        }

        BR4Prerequisites cyclic = new BR4Prerequisites();
        cyclic.addPrerequisite(10, 11, 1);
        cyclic.addPrerequisite(11, 12, 1);
        cyclic.addPrerequisite(12, 10, 1);
        System.out.println("Цикл в отдельном графе: " + cyclic.findCycle());
    }

    private static void demoBR5() {
        System.out.println();
        System.out.println("BR-5: план семестра и расписание");
        final List<Course> courses = List.of(
                new Course(1, "Java Basics", 30, 50),
                new Course(2, "Алгоритмы", 50, 90),
                new Course(3, "Базы данных", 25, 40),
                new Course(4, "Веб-разработка", 40, 60),
                new Course(5, "Машинное обучение", 60, 95)
        );
        final int maxHours = 90;
        System.out.println("Максимум часов: " + maxHours);

        long optimalMs = Timer.measure("оптимальный план", new Runnable() {
            @Override
            public void run() {
                List<Course> plan = SEMESTER_PLAN.buildOptimalPlan(courses, maxHours);
                System.out.println("Курсы: " + plan);
                System.out.println("Польза: " + SEMESTER_PLAN.totalBenefit(plan));
                System.out.println("Часы: " + SEMESTER_PLAN.totalHours(plan));
            }
        });

        long greedyMs = Timer.measure("быстрый план", new Runnable() {
            @Override
            public void run() {
                List<Course> plan = SEMESTER_PLAN.buildFastPlan(courses, maxHours);
                System.out.println("Курсы: " + plan);
                System.out.println("Польза: " + SEMESTER_PLAN.totalBenefit(plan));
                System.out.println("Часы: " + SEMESTER_PLAN.totalHours(plan));
            }
        });

        Timer.printComparison("план семестра", optimalMs, greedyMs);

        System.out.println();
        System.out.println("Набор, где жадный проигрывает оптимальному:");
        List<Course> tricky = List.of(
                new Course(1, "A", 10, 60),
                new Course(2, "B", 20, 100),
                new Course(3, "C", 30, 120)
        );
        int capacity = 50;
        List<Course> optimalPlan = SEMESTER_PLAN.buildOptimalPlan(tricky, capacity);
        List<Course> greedyPlan = SEMESTER_PLAN.buildFastPlan(tricky, capacity);
        System.out.println("Оптимальный план: " + optimalPlan);
        System.out.println(" польза = " + SEMESTER_PLAN.totalBenefit(optimalPlan)
                + ", часы = " + SEMESTER_PLAN.totalHours(optimalPlan));
        System.out.println("Быстрый план: " + greedyPlan);
        System.out.println(" польза = " + SEMESTER_PLAN.totalBenefit(greedyPlan)
                + ", часы = " + SEMESTER_PLAN.totalHours(greedyPlan));

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
        System.out.println("Выбрано занятий: " + selected.size());
        for (IntervalScheduling.Interval interval : selected) {
            System.out.println(" " + interval);
        }
    }

    private static void demoBR6() {
        System.out.println();
        System.out.println("BR-6: аналитика активности");
        final int days = 100_000;
        final int windowSize = 7;
        final List<Integer> activity = DataGenerator.generateDailyActivity(days, 1000);

        long fastMs = Timer.measure("скользящее окно O(n)", new Runnable() {
            @Override
            public void run() {
                ANALYTICS.busiestWindow(activity, windowSize);
            }
        });

        long naiveMs = Timer.measure("наивный O(n*windowSize)", new Runnable() {
            @Override
            public void run() {
                ANALYTICS.busiestWindowNaive(activity, windowSize);
            }
        });

        SlidingWindow.WindowResult fast = ANALYTICS.busiestWindow(activity, windowSize);
        SlidingWindow.WindowResult naive = ANALYTICS.busiestWindowNaive(activity, windowSize);
        System.out.println("Скользящее окно: " + fast);
        System.out.println("Наивный результат: " + naive);
        System.out.println("Результаты совпадают: " + (fast.sum == naive.sum));
        Timer.printComparison("окно", fastMs, naiveMs);

        System.out.println();
        System.out.println("Поиск пары с заданной суммой на маленьком массиве:");
        List<Integer> sample = List.of(2, 7, 11, 15);
        int sampleTarget = 9;
        TwoSum.Pair samplePair = ANALYTICS.findPairExact(sample, sampleTarget);
        System.out.println("Массив " + sample + ", цель " + sampleTarget + ": " + samplePair);

        System.out.println();
        System.out.println("Поиск пары на большом массиве:");
        List<Integer> scores = DataGenerator.generateScores(10_000, 5000);
        int target = 7000;
        TwoSum.Pair exact = ANALYTICS.findPairExact(scores, target);
        System.out.println("Точная пара на " + target + ": " + exact);
        TwoSum.Pair closest = ANALYTICS.findPairClosest(scores, target);
        System.out.println("Ближайшая пара на " + target + ": " + closest);
    }

    private static void demoBR7() {
        System.out.println();
        System.out.println("BR-7: траектории обучения");
        System.out.println("Сравнение наивного и оптимизированного расчёта:");
        System.out.println("N    наивно              оптимизировано");
        for (int n = 10; n <= 30; n += 5) {
            BigInteger naive = TRAJECTORIES.countNaive(n);
            BigInteger optimized = TRAJECTORIES.countOptimized(n);
            System.out.println(n + "    " + naive + "    " + optimized);
        }

        final int smallN = 25;
        System.out.println();
        System.out.println("Замер на N = " + smallN + ":");
        long naiveMs = Timer.measure("наивный O(3^n), N = " + smallN, new Runnable() {
            @Override
            public void run() {
                TRAJECTORIES.countNaive(smallN);
            }
        });
        long optimizedMs = Timer.measure("DP O(n), N = " + smallN, new Runnable() {
            @Override
            public void run() {
                TRAJECTORIES.countOptimized(smallN);
            }
        });
        Timer.printComparison("траектории N = " + smallN, naiveMs, optimizedMs);

        final int largeN = 80;
        System.out.println();
        System.out.println("Оптимизированный расчёт для N = " + largeN + ":");
        Timer.measure("DP O(n), N = " + largeN, new Runnable() {
            @Override
            public void run() {
                TRAJECTORIES.countOptimized(largeN);
            }
        });
        System.out.println("Наивный расчёт для N = " + largeN + " не запускается:");
        System.out.println("сложность O(3^" + largeN + ") делает его недопустимо медленным.");
    }

    private static void demoEndToEnd() {
        System.out.println();
        System.out.println("Сквозной сценарий");
        BR1Journal journal = new BR1Journal();
        BR2Registry registry = new BR2Registry();
        BR3Rating rating = new BR3Rating();
        BR4Prerequisites prerequisites = new BR4Prerequisites();
        BR5SemesterPlan semesterPlan = new BR5SemesterPlan();

        System.out.println("1. Регистрируем студента Ивана");
        Student ivan = new Student(101, "Иван", 0);
        registry.add(ivan);
        journal.record(new Action("Добавлен студент Иван", new Runnable() {
            @Override
            public void run() {
                System.out.println("откат: студент Иван удалён");
            }
        }));

        System.out.println("2. Начисляем баллы");
        ivan.setScore(500);
        journal.record(new Action("Начислено 500 баллов Ивану", new Runnable() {
            @Override
            public void run() {
                System.out.println("откат: баллы сняты");
            }
        }));

        System.out.println("3. Перестраиваем рейтинг по одному студенту...");
        List<Student> students = new ArrayList<>();
        students.add(ivan);
        rating.rebuild(students);
        System.out.println("Место Ивана: " + rating.placeOf(ivan));

        System.out.println("4. Добавляем курсы и пререквизиты...");
        prerequisites.addPrerequisite(1, 2, 20);
        prerequisites.addPrerequisite(2, 3, 30);
        System.out.println("Доступно после курса 1: "
                + prerequisites.reachableFrom(1));

        System.out.println("5. Строим план семестра...");
        List<Course> courses = DataGenerator.generateCourses(5);
        List<Course> plan = semesterPlan.buildOptimalPlan(courses, 50);
        System.out.println("План: " + plan);

        System.out.println("6. Откатываем последнее действие");
        String undone = journal.undoLast();
        System.out.println("Откачено: " + undone);

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