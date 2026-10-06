package com.example.learnhub.algorithms;

import com.example.learnhub.model.Course;

import java.util.ArrayList;
import java.util.List;

public final class Knapsack {

    private Knapsack() {
    }

    public static List<Course> solveOptimal(List<Course> courses, int maxHours) {
        int count = courses.size();
        int[][] dp = new int[count + 1][maxHours + 1];

        for (int i = 1; i <= count; i++) {
            Course course = courses.get(i - 1);
            for (int hours = 0; hours <= maxHours; hours++) {
                dp[i][hours] = dp[i - 1][hours];
                if (course.getHours() <= hours) {
                    int withoutCourse = dp[i - 1][hours];
                    int withCourse = dp[i - 1][hours - course.getHours()] + course.getBenefit();
                    if (withCourse > withoutCourse) {
                        dp[i][hours] = withCourse;
                    }
                }
            }
        }

        List<Course> result = new ArrayList<>();
        int hours = maxHours;
        for (int i = count; i > 0; i--) {
            if (dp[i][hours] != dp[i - 1][hours]) {
                Course course = courses.get(i - 1);
                result.add(course);
                hours -= course.getHours();
            }
        }
        return result;
    }

    public static List<Course> solveGreedy(List<Course> courses, int maxHours) {
        List<Course> sorted = new ArrayList<>(courses);
        for (int i = 1; i < sorted.size(); i++) {
            Course current = sorted.get(i);
            double currentRatio = (double) current.getBenefit() / current.getHours();
            int j = i - 1;
            while (j >= 0) {
                double otherRatio = (double) sorted.get(j).getBenefit() / sorted.get(j).getHours();
                if (otherRatio >= currentRatio) {
                    break;
                }
                sorted.set(j + 1, sorted.get(j));
                j--;
            }
            sorted.set(j + 1, current);
        }

        List<Course> result = new ArrayList<>();
        int remaining = maxHours;
        for (Course course : sorted) {
            if (course.getHours() <= remaining) {
                result.add(course);
                remaining -= course.getHours();
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