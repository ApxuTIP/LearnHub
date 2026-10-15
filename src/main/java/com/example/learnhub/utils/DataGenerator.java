package com.example.learnhub.utils;

import com.example.learnhub.model.Course;
import com.example.learnhub.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class DataGenerator {
    private static final long STUDENT_SEED = 42L;
    private static final long COURSE_SEED = 43L;
    private static final long ACTIVITY_SEED = 44L;
    private static final long SCORE_SEED = 45L;
    private static final int STUDENT_SCORE_BOUND = 1000;
    private static final int COURSE_MIN_HOURS = 5;
    private static final int COURSE_HOURS_RANGE = 40;
    private static final int COURSE_MIN_BENEFIT = 10;
    private static final int COURSE_BENEFIT_RANGE = 90;

    private DataGenerator() {
    }

    public static List<Student> generateStudents(int count) {
        Random random = new Random(STUDENT_SEED);
        List<Integer> identifiers = new ArrayList<>(count);
        for (int identifier = 1; identifier <= count; identifier++) {
            identifiers.add(identifier);
        }

        Collections.shuffle(identifiers, random);
        List<Student> students = new ArrayList<>(count);

        for (int index = 0; index < count; index++) {
            int studentId = identifiers.get(index);
            String studentName = "Студент-" + studentId;
            int score = random.nextInt(STUDENT_SCORE_BOUND);
            students.add(new Student(studentId, studentName, score));
        }
        return students;
    }

    public static List<Course> generateCourses(int count) {
        Random random = new Random(COURSE_SEED);
        List<Course> courses = new ArrayList<>(count);
        for (int index = 0; index < count; index++) {
            int courseId = index + 1;
            String courseTitle = "Курс-" + courseId;
            int hours = COURSE_MIN_HOURS + random.nextInt(COURSE_HOURS_RANGE);
            int benefit = COURSE_MIN_BENEFIT + random.nextInt(COURSE_BENEFIT_RANGE);
            courses.add(new Course(courseId, courseTitle, hours, benefit));
        }
        return courses;
    }

    public static List<Integer> generateDailyActivity(int days, int maximumValue) {
        Random random = new Random(ACTIVITY_SEED);
        List<Integer> activity = new ArrayList<>(days);
        for (int index = 0; index < days; index++) {
            activity.add(random.nextInt(maximumValue));
        }
        return activity;
    }

    public static List<Integer> generateScores(int count, int maximumValue) {
        Random random = new Random(SCORE_SEED);
        List<Integer> scores = new ArrayList<>(count);
        for (int index = 0; index < count; index++) {
            scores.add(random.nextInt(maximumValue));
        }
        return scores;
    }
}