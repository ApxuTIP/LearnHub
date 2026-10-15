package com.example.learnhub.model;

public class Lesson {
    private final int lessonId;
    private final String lessonTitle;

    public Lesson(int lessonId, String lessonTitle) {
        this.lessonId = lessonId;
        this.lessonTitle = lessonTitle;
    }

    public int getLessonId() {
        return lessonId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public String toString() {
        return "Lesson{lessonId=" + lessonId + ", lessonTitle='" + lessonTitle + "'}";
    }
}