package com.example.learnhub.demonstrations;

import com.example.learnhub.services.TrajectoryService;
import com.example.learnhub.utils.Timer;

import java.math.BigInteger;

public class TrajectoriesDemonstration {

    private static final int TABLE_START = 10;
    private static final int TABLE_END = 30;
    private static final int TABLE_STEP = 5;
    private static final int SMALL_MODULE_COUNT = 25;
    private static final int LARGE_MODULE_COUNT = 80;

    public void run() {
        System.out.println();
        System.out.println("BR-7: траектории обучения");
        System.out.println("За семестр можно пройти один или два модуля.");
        System.out.println("Формула: f(n) = f(n-1) + f(n-2), f(0) = f(1) = 1.");
        System.out.println();
        System.out.println("Проверка формулы: f(2) = 2, f(3) = 3, f(4) = 5, f(5) = 8.");
        System.out.println();
        System.out.println("N наивно оптимизировано");

        TrajectoryService trajectoryService = new TrajectoryService();

        for (int moduleCount = TABLE_START;
             moduleCount <= TABLE_END;
             moduleCount += TABLE_STEP) {
            BigInteger naive = trajectoryService.countNaive(moduleCount);
            BigInteger optimized = trajectoryService.countOptimized(moduleCount);
            System.out.println(moduleCount + " " + naive + " " + optimized);
        }

        System.out.println();
        System.out.println("Замер на N = " + SMALL_MODULE_COUNT + ":");
        long naiveMilliseconds = Timer.measureSilent(() ->
                trajectoryService.countNaive(SMALL_MODULE_COUNT));
        long optimizedMilliseconds = Timer.measureSilent(() ->
                trajectoryService.countOptimized(SMALL_MODULE_COUNT));
        Timer.printComparison("траектории N = " + SMALL_MODULE_COUNT,
                "наивная рекурсия O(2^n)", naiveMilliseconds,
                "рекурсия с мемоизацией O(n)", optimizedMilliseconds);

        System.out.println();
        System.out.println("Оптимизированный расчёт для N = " + LARGE_MODULE_COUNT + ":");
        BigInteger largeResult = trajectoryService.countOptimized(LARGE_MODULE_COUNT);
        System.out.println("результат: " + largeResult);
        long largeMilliseconds = Timer.measureSilent(() ->
                trajectoryService.countOptimized(LARGE_MODULE_COUNT));
        System.out.println("время: " + largeMilliseconds + " мс");
        System.out.println("Наивный расчёт для N = " + LARGE_MODULE_COUNT + " не запускается:");
        System.out.println("сложность O(2^" + LARGE_MODULE_COUNT + ") делает его недопустимо медленным.");
    }
}