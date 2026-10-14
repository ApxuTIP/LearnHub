package com.example.learnhub.br;

import com.example.learnhub.algorithms.Searching;
import com.example.learnhub.algorithms.Sorting;
import com.example.learnhub.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BR3Rating {

    private static final Comparator<Student> BY_SCORE_DESC_THEN_ID_ASC =
            (first, second) -> {
                int scoreComparison = Integer.compare(second.getScore(), first.getScore());
                if (scoreComparison != 0) {
                    return scoreComparison;
                }
                return Integer.compare(first.getStudentId(), second.getStudentId());
            };

    private final List<Student> sortedByScore = new ArrayList<>();

    public void rebuild(List<Student> students) {
        sortedByScore.clear();
        sortedByScore.addAll(students);
        Sorting.mergeSort(sortedByScore, BY_SCORE_DESC_THEN_ID_ASC);
    }

    public int placeOf(Student target) {
        int index = Searching.binarySearch(sortedByScore, BY_SCORE_DESC_THEN_ID_ASC, target);
        int place = -1;
        if (index >= 0) {
            place = index + 1;
        }
        return place;
    }

    public List<Student> inScoreRange(int low, int high) {
        List<Student> result = new ArrayList<>();
        for (Student student : sortedByScore) {
            int score = student.getScore();
            if (score < low) {
                break;
            }
            if (score <= high) {
                result.add(student);
            }
        }
        return result;
    }

    public Student firstAtLeast(int threshold) {
        Student result = null;
        if (!sortedByScore.isEmpty()) {
            Student highest = sortedByScore.get(0);
            if (highest.getScore() >= threshold) {
                result = highest;
            }
        }
        return result;
    }

    public List<Student> all() {
        return new ArrayList<>(sortedByScore);
    }

    public int size() {
        return sortedByScore.size();
    }
}