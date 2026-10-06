package com.example.learnhub.br;

import com.example.learnhub.algorithms.IntervalScheduling;
import com.example.learnhub.algorithms.Knapsack;
import com.example.learnhub.model.Course;

import java.util.List;

public class BR5SemesterPlan {

    public List<Course> buildOptimalPlan(List<Course> courses, int maxHours) {
        return Knapsack.solveOptimal(courses, maxHours);
    }

    public List<Course> buildFastPlan(List<Course> courses, int maxHours) {
        return Knapsack.solveGreedy(courses, maxHours);
    }

    public List<IntervalScheduling.Interval> buildLectureSchedule(
            List<IntervalScheduling.Interval> intervals) {
        return IntervalScheduling.selectMax(intervals);
    }

    public int totalBenefit(List<Course> courses) {
        return Knapsack.totalBenefit(courses);
    }

    public int totalHours(List<Course> courses) {
        return Knapsack.totalHours(courses);
    }
}