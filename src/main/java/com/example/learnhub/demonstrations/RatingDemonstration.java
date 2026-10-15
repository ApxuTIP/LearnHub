package com.example.learnhub.demonstrations;

import com.example.learnhub.algorithms.Sorter;
import com.example.learnhub.model.Student;
import com.example.learnhub.services.RatingService;
import com.example.learnhub.utils.Comparators;
import com.example.learnhub.utils.DataGenerator;
import com.example.learnhub.utils.InputValidator;
import com.example.learnhub.utils.Timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RatingDemonstration {
    private static final int RATING_STUDENT_COUNT = 100_000;
    private static final int RANDOM_BOUND = 1_000_000;
    private static final int RANDOM_SEED = 42;
    private static final int SHOW_LIMIT = 5;
    private static final int[] SORT_TABLE_SIZES = {100, 1_000, 10_000, 100_000};

    private final Scanner scanner;

    public RatingDemonstration(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.println();
        System.out.println("BR-3: рейтинг и поиск");
        System.out.println("Генерируем " + RATING_STUDENT_COUNT + " студентов...");
        List<Student> students = DataGenerator.generateStudents(RATING_STUDENT_COUNT);

        System.out.println("Замеры сортировки на разных объёмах и разных данных:");
        printSortingComparisonTable();

        RatingService ratingService = new RatingService();
        long rebuildMilliseconds = Timer.measureSilent(() -> ratingService.rebuild(students));
        System.out.println("Построение рейтинга mergeSort на " + RATING_STUDENT_COUNT + ": " + rebuildMilliseconds + " мс");
        System.out.println("Размер рейтинга: " + ratingService.size());

        int minimumScore = InputValidator.readInt(scanner, "Введите нижнюю границу балла: ");
        int maximumScore = InputValidator.readInt(scanner, "Введите верхнюю границу балла: ");

        if (minimumScore > maximumScore) {
            int temporary = minimumScore;
            minimumScore = maximumScore;
            maximumScore = temporary;
            System.out.println("Границы были перепутаны, поменяли местами: [" + minimumScore + "; " + maximumScore + "]");
        }

        List<Student> range = ratingService.inScoreRange(minimumScore, maximumScore);
        System.out.println("Найдено студентов в диапазоне: " + range.size());
        int showCount = Math.min(SHOW_LIMIT, range.size());

        for (int index = 0; index < showCount; index++) {
            System.out.println("  " + range.get(index));
        }

        int threshold = InputValidator.readInt(scanner, "Введите порог для поиска первого студента: ");
        Student firstStudent = ratingService.firstAtLeast(threshold);
        System.out.println("Первый студент с баллом >= " + threshold + ": " + firstStudent);

        if (!students.isEmpty()) {
            Student sample = students.get(0);
            System.out.println("Место студента " + sample + " в рейтинге: " + ratingService.placeOf(sample));
        }
    }

    private void printSortingComparisonTable() {
        System.out.println("объём тип данных insertionSort mergeSort adaptiveSort");

        for (int size : SORT_TABLE_SIZES) {
            List<Integer> randomData = generateRandomList(size);
            List<Integer> sortedData = generateSortedList(size);

            long insertionRandomMilliseconds = Timer.measureSilent(() ->
                    Sorter.insertionSort(new ArrayList<>(randomData),
                            new Comparators.IntegerAscending()));
            long mergeRandomMilliseconds = Timer.measureSilent(() ->
                    Sorter.mergeSort(new ArrayList<>(randomData),
                            new Comparators.IntegerAscending()));
            long adaptiveRandomMilliseconds = Timer.measureSilent(() ->
                    Sorter.adaptiveSort(new ArrayList<>(randomData),
                            new Comparators.IntegerAscending()));

            System.out.println(" " + size + "случайные"
                    + insertionRandomMilliseconds + " мс"
                    + mergeRandomMilliseconds + " мс"
                    + adaptiveRandomMilliseconds + " мс");

            long insertionSortedMilliseconds = Timer.measureSilent(() ->
                    Sorter.insertionSort(new ArrayList<>(sortedData),
                            new Comparators.IntegerAscending()));
            long mergeSortedMilliseconds = Timer.measureSilent(() ->
                    Sorter.mergeSort(new ArrayList<>(sortedData),
                            new Comparators.IntegerAscending()));
            long adaptiveSortedMilliseconds = Timer.measureSilent(() ->
                    Sorter.adaptiveSort(new ArrayList<>(sortedData),
                            new Comparators.IntegerAscending()));

            System.out.println(" " + size + "отсортированные"
                    + insertionSortedMilliseconds + " мс"
                    + mergeSortedMilliseconds + " мс"
                    + adaptiveSortedMilliseconds + " мс");
        }
    }

    private List<Integer> generateRandomList(int size) {
        Random random = new Random(RANDOM_SEED);
        List<Integer> data = new ArrayList<>(size);
        for (int index = 0; index < size; index++) {
            data.add(random.nextInt(RANDOM_BOUND));
        }
        return data;
    }

    private List<Integer> generateSortedList(int size) {
        List<Integer> data = new ArrayList<>(size);
        for (int index = 0; index < size; index++) {
            data.add(index);
        }
        return data;
    }
}