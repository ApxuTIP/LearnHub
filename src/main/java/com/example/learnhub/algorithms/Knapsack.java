package com.example.learnhub.algorithms;

import com.example.learnhub.model.Course;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Knapsack {

    private static final Comparator<Course> BY_BENEFIT_PER_HOUR_DESC =
            (first, second) -> {
                double firstRatio = (double) first.getBenefit() / first.getHours();
                double secondRatio = (double) second.getBenefit() / second.getHours();
                return Double.compare(secondRatio, firstRatio);
            };

    private Knapsack() {
    }

    public static List<Course> solveOptimal(List<Course> courses, int maxHours) {
        int count = courses.size();
        int[][] bestUsefulness = new int[count + 1][maxHours + 1];

        for (int itemIndex = 1; itemIndex <= count; itemIndex++) {
            Course course = courses.get(itemIndex - 1);
            for (int hours = 0; hours <= maxHours; hours++) {
                bestUsefulness[itemIndex][hours] = bestUsefulness[itemIndex - 1][hours];
                if (course.getHours() <= hours) {
                    int withoutCourse = bestUsefulness[itemIndex - 1][hours];
                    int withCourse = bestUsefulness[itemIndex - 1][hours - course.getHours()]
                            + course.getBenefit();
                    if (withCourse > withoutCourse) {
                        bestUsefulness[itemIndex][hours] = withCourse;
                    }
                }
            }
        }

        List<Course> result = new ArrayList<>();
        int hours = maxHours;
        for (int itemIndex = count; itemIndex > 0; itemIndex--) {
            if (bestUsefulness[itemIndex][hours] != bestUsefulness[itemIndex - 1][hours]) {
                Course course = courses.get(itemIndex - 1);
                result.add(course);
                hours -= course.getHours();
            }
        }
        return result;
    }

    public static List<Course> solveGreedy(List<Course> courses, int maxHours) {
        List<Course> sorted = new ArrayList<>(courses);
        Sorting.mergeSort(sorted, BY_BENEFIT_PER_HOUR_DESC);

        List<Course> result = new ArrayList<>();
        int remainingHours = maxHours;
        for (Course course : sorted) {
            if (course.getHours() <= remainingHours) {
                result.add(course);
                remainingHours -= course.getHours();
            }
        }
        return result;
    }

    public static int totalBenefit(List<Course> courses) {
        int sum = 0;
        for (Course course : courses) {
            sum += course.getBenefit();
        }
        return sum;
    }

    public static int totalHours(List<Course> courses) {
        int sum = 0;
        for (Course course : courses) {
            sum += course.getHours();
        }
        return sum;
    }
}