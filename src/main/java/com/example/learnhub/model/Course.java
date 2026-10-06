package com.example.learnhub.model;

public class Course implements Comparable<Course> {

    private final int id;
    private final String title;
    private final int hours;
    private final int benefit;

    public Course(int id, String title, int hours, int benefit) {
        this.id = id;
        this.title = title;
        this.hours = hours;
        this.benefit = benefit;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getHours() {
        return hours;
    }

    public int getBenefit() {
        return benefit;
    }

    @Override
    public int compareTo(Course other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Course{id=" + id
                + ", title='" + title + "'"
                + ", hours=" + hours
                + ", benefit=" + benefit + "}";
    }
}