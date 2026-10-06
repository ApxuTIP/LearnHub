package com.example.learnhub.util;

import com.example.learnhub.model.Course;
import com.example.learnhub.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class DataGenerator {

    private static final long DEFAULT_SEED = 42L;

    private DataGenerator() {
    }

    public static List<Student> generateStudents(int count) {
        Random random = new Random(DEFAULT_SEED);
        List<Student> students = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            int id = i + 1;
            String name = "Студент-" + id;
            int score = random.nextInt(1000);
            students.add(new Student(id, name, score));
        }
        return students;
    }

    public static List<Course> generateCourses(int count) {
        Random random = new Random(DEFAULT_SEED + 1);
        List<Course> courses = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            int id = i + 1;
            String title = "Курс-" + id;
            int hours = 5 + random.nextInt(40);
            int benefit = 10 + random.nextInt(90);
            courses.add(new Course(id, title, hours, benefit));
        }
        return courses;
    }

    public static List<Integer> generateDailyActivity(int days, int bound) {
        Random random = new Random(DEFAULT_SEED + 2);
        List<Integer> activity = new ArrayList<>(days);
        for (int i = 0; i < days; i++) {
            activity.add(random.nextInt(bound));
        }
        return activity;
    }

    public static List<Integer> generateScores(int count, int bound) {
        Random random = new Random(DEFAULT_SEED + 3);
        List<Integer> scores = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            scores.add(random.nextInt(bound));
        }
        return scores;
    }
}