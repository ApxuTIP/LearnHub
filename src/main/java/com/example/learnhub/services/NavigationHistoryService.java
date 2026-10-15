package com.example.learnhub.services;

import com.example.learnhub.model.Lesson;
import com.example.learnhub.structures.Stack;

import java.util.ArrayList;
import java.util.List;

public class NavigationHistoryService {
    private final Stack<Lesson> viewedLessons = new Stack<>();

    public void visitLesson(Lesson lesson) {
        viewedLessons.push(lesson);
    }

    public Lesson goBack() {
        Lesson previousLesson = null;
        if (!viewedLessons.isEmpty()) {
            previousLesson = viewedLessons.pop();
        }
        return previousLesson;
    }

    public Lesson currentLesson() {
        Lesson currentLesson = null;
        if (!viewedLessons.isEmpty()) {
            currentLesson = viewedLessons.peek();
        }
        return currentLesson;
    }

    public int size() {
        return viewedLessons.size();
    }

    public boolean isEmpty() {
        return viewedLessons.isEmpty();
    }

    public List<String> snapshot() {
        List<String> lessonTitles = new ArrayList<>();
        for (Lesson lesson : viewedLessons) {
            lessonTitles.add(lesson.getLessonTitle());
        }
        return lessonTitles;
    }
}