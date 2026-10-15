package com.example.learnhub.model;

public class Course implements Comparable<Course> {
    private final int courseId;
    private final String courseTitle;
    private final int hours;
    private final int benefit;

    public Course(int courseId, String courseTitle, int hours, int benefit) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.hours = hours;
        this.benefit = benefit;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public int getHours() {
        return hours;
    }

    public int getBenefit() {
        return benefit;
    }

    @Override
    public int compareTo(Course otherCourse) {
        return Integer.compare(this.courseId, otherCourse.courseId);
    }

    @Override
    public String toString() {
        return "Course{courseId=" + courseId
                + ", courseTitle='" + courseTitle + "'"
                + ", hours=" + hours
                + ", benefit=" + benefit + "}";
    }
}