package com.example.learnhub.services;

import com.example.learnhub.algorithms.IntervalScheduling;
import com.example.learnhub.algorithms.Knapsack;
import com.example.learnhub.model.Course;

import java.util.List;

public class SemesterPlanningService {
    public List<Course> buildOptimalPlan(List<Course> courses, int maximumHours) {
        return Knapsack.solveOptimal(courses, maximumHours);
    }

    public List<Course> buildFastPlan(List<Course> courses, int maximumHours) {
        return Knapsack.solveGreedy(courses, maximumHours);
    }

    public List<IntervalScheduling.Interval> buildLectureSchedule(List<IntervalScheduling.Interval> intervals) {
        return IntervalScheduling.selectMax(intervals);
    }

    public int totalBenefit(List<Course> courses) {
        return Knapsack.totalBenefit(courses);
    }

    public int totalHours(List<Course> courses) {
        return Knapsack.totalHours(courses);
    }
}