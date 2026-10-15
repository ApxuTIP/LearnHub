package com.example.learnhub.algorithms;

import com.example.learnhub.model.Course;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Knapsack {
    private static class ByBenefitPerHourDescendingComparator implements Comparator<Course> {
        @Override
        public int compare(Course firstCourse, Course secondCourse) {
            double firstRatio = (double) firstCourse.getBenefit() / firstCourse.getHours();
            double secondRatio = (double) secondCourse.getBenefit() / secondCourse.getHours();
            return Double.compare(secondRatio, firstRatio);
        }
    }

    private Knapsack() {
    }

    public static List<Course> solveOptimal(List<Course> courses, int maxHours) {
        int courseCount = courses.size();
        int[][] bestUsefulness = new int[courseCount + 1][maxHours + 1];
        for (int itemIndex = 1; itemIndex <= courseCount; itemIndex++) {
            Course course = courses.get(itemIndex - 1);
            for (int hours = 0; hours <= maxHours; hours++) {
                bestUsefulness[itemIndex][hours] = bestUsefulness[itemIndex - 1][hours];
                if (course.getHours() <= hours) {
                    int usefulnessWithoutCourse = bestUsefulness[itemIndex - 1][hours];
                    int usefulnessWithCourse = bestUsefulness[itemIndex - 1][hours - course.getHours()] + course.getBenefit();
                    if (usefulnessWithCourse > usefulnessWithoutCourse) {
                        bestUsefulness[itemIndex][hours] = usefulnessWithCourse;
                    }
                }
            }
        }

        List<Course> result = new ArrayList<>();
        int remainingHours = maxHours;
        for (int itemIndex = courseCount; itemIndex > 0; itemIndex--) {
            if (bestUsefulness[itemIndex][remainingHours]
                    != bestUsefulness[itemIndex - 1][remainingHours]) {
                Course course = courses.get(itemIndex - 1);
                result.add(course);
                remainingHours -= course.getHours();
            }
        }
        return result;
    }

    public static List<Course> solveGreedy(List<Course> courses, int maxHours) {
        List<Course> sortedCourses = new ArrayList<>(courses);
        Sorter.mergeSort(sortedCourses, new ByBenefitPerHourDescendingComparator());

        List<Course> result = new ArrayList<>();
        int remainingHours = maxHours;
        for (Course course : sortedCourses) {
            if (course.getHours() <= remainingHours) {
                result.add(course);
                remainingHours -= course.getHours();
            }
        }
        return result;
    }

    public static int totalBenefit(List<Course> courses) {
        int benefitSum = 0;
        for (Course course : courses) {
            benefitSum += course.getBenefit();
        }
        return benefitSum;
    }

    public static int totalHours(List<Course> courses) {
        int hoursSum = 0;
        for (Course course : courses) {
            hoursSum += course.getHours();
        }
        return hoursSum;
    }
}