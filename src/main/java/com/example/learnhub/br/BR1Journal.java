package com.example.learnhub.br;

import com.example.learnhub.model.Lesson;
import com.example.learnhub.structures.CustomStack;

import java.util.ArrayList;
import java.util.List;

public class BR1Journal {

    private final CustomStack<Lesson> viewedLessons = new CustomStack<>();

    public void visitLesson(Lesson lesson) {
        viewedLessons.push(lesson);
    }

    public Lesson goBack() {
        if (viewedLessons.isEmpty()) {
            return null;
        }
        return viewedLessons.pop();
    }

    public Lesson currentLesson() {
        if (viewedLessons.isEmpty()) {
            return null;
        }
        return viewedLessons.peek();
    }

    public int size() {
        return viewedLessons.size();
    }

    public boolean isEmpty() {
        return viewedLessons.isEmpty();
    }

    public void clear() {
        viewedLessons.clear();
    }

    public List<String> snapshot() {
        List<String> result = new ArrayList<>();
        for (Lesson lesson : viewedLessons) {
            result.add(lesson.getLessonTitle());
        }
        return result;
    }
}