package com.example.learnhub.services;

import com.example.learnhub.model.Lesson;
import com.example.learnhub.structures.CustomStack;

import java.util.ArrayList;
import java.util.List;

public class NavigationHistoryService {
    private final CustomStack<Lesson> viewedLessons = new CustomStack<>();
    public void visitLesson(Lesson lesson) {
        viewedLessons.push(lesson);
    }

    public Lesson goBack() {
        Lesson previousLesson = null;
        if (!viewedLessons.isEmpty()) {
            viewedLessons.pop();
            if (!viewedLessons.isEmpty()) {
                previousLesson = viewedLessons.peek();
            }
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
