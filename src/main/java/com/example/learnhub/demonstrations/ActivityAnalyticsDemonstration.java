package com.example.learnhub.demonstrations;

import com.example.learnhub.algorithms.ClosestPairFinder;
import com.example.learnhub.algorithms.SlidingWindow;
import com.example.learnhub.services.ActivityAnalyticsService;
import com.example.learnhub.utils.DataGenerator;
import com.example.learnhub.utils.Timer;

import java.util.List;

public class ActivityAnalyticsDemonstration {
    private static final int ANALYTICS_DAYS = 100_000;
    private static final int ANALYTICS_WINDOW_SIZE = 7;
    private static final int ACTIVITY_BOUND = 1000;
    private static final int PAIR_SAMPLE_SIZE = 10_000;
    private static final int PAIR_SCORE_BOUND = 5000;
    private static final int PAIR_TARGET_SUM = 7000;

    public void run() {
        System.out.println();
        System.out.println("BR-6: аналитика активности");
        ActivityAnalyticsService analyticsService = new ActivityAnalyticsService();
        List<Integer> activity = DataGenerator.generateDailyActivity(ANALYTICS_DAYS, ACTIVITY_BOUND);
        SlidingWindow.WindowResult fastWindow = analyticsService.busiestWindow(activity, ANALYTICS_WINDOW_SIZE);
        SlidingWindow.WindowResult naiveWindow = analyticsService.busiestWindowNaive(activity, ANALYTICS_WINDOW_SIZE);
        long fastMilliseconds = Timer.measureSilent(() -> analyticsService.busiestWindow(activity, ANALYTICS_WINDOW_SIZE));
        long naiveMilliseconds = Timer.measureSilent(() -> analyticsService.busiestWindowNaive(activity, ANALYTICS_WINDOW_SIZE));
        System.out.println("Скользящее окно: " + fastWindow);
        System.out.println("Наивный результат: " + naiveWindow);
        System.out.println("Суммы совпадают: " + (fastWindow.getWindowSum() == naiveWindow.getWindowSum()));
        Timer.printComparison("окно", "скользящее окно O(n)", fastMilliseconds, "наивный O(n·k)", naiveMilliseconds);

        System.out.println();
        System.out.println("Поиск пары с заданной суммой (точное совпадение):");
        List<Integer> sample = List.of(2, 7, 11, 15);
        int sampleTargetSum = 9;
        ClosestPairFinder.Pair samplePair = analyticsService.findPairExact(sample, sampleTargetSum);
        System.out.println("массив " + sample + ", цель " + sampleTargetSum + ": " + samplePair);

        System.out.println();
        System.out.println("Поиск ближайшей пары на большом массиве:");
        List<Integer> scores = DataGenerator.generateScores(PAIR_SAMPLE_SIZE, PAIR_SCORE_BOUND);
        ClosestPairFinder.Pair closestFast = analyticsService.findPairClosest(scores, PAIR_TARGET_SUM);
        ClosestPairFinder.Pair closestNaive = analyticsService.findPairClosestNaive(scores, PAIR_TARGET_SUM);
        long closestFastMilliseconds = Timer.measureSilent(() -> analyticsService.findPairClosest(scores, PAIR_TARGET_SUM));
        long closestNaiveMilliseconds = Timer.measureSilent(() -> analyticsService.findPairClosestNaive(scores, PAIR_TARGET_SUM));
        System.out.println("быстрый (mergeSort + два указателя): " + closestFast);
        System.out.println("наивный (перебор): " + closestNaive);
        int fastSum = closestFast.getFirstValue() + closestFast.getSecondValue();
        int naiveSum = closestNaive.getFirstValue() + closestNaive.getSecondValue();
        System.out.println("суммы совпадают: " + (fastSum == naiveSum));
        Timer.printComparison("ближайшая пара", "mergeSort + два указателя", closestFastMilliseconds, "наивный перебор", closestNaiveMilliseconds);
    }
}