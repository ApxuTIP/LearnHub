package com.example.learnhub.services;

import com.example.learnhub.algorithms.BinarySearch;
import com.example.learnhub.algorithms.Sorter;
import com.example.learnhub.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RatingService {
    private final List<Student> sortedByScore = new ArrayList<>();
    private static class ByScoreDescendingThenIdAscendingComparator implements Comparator<Student> {
        @Override
        public int compare(Student firstStudent, Student secondStudent) {
            int scoreComparison = Integer.compare(secondStudent.getScore(), firstStudent.getScore());
            if (scoreComparison != 0) {
                return scoreComparison;
            }
            return Integer.compare(firstStudent.getStudentId(), secondStudent.getStudentId());
        }
    }

    public void rebuild(List<Student> students) {
        sortedByScore.clear();
        sortedByScore.addAll(students);
        Sorter.mergeSort(sortedByScore, new ByScoreDescendingThenIdAscendingComparator());
    }

    public int placeOf(Student targetStudent) {
        int index = BinarySearch.binarySearch(sortedByScore, new ByScoreDescendingThenIdAscendingComparator(), targetStudent);
        int place = -1;
        if (index >= 0) {
            place = index + 1;
        }
        return place;
    }

    public List<Student> inScoreRange(int minimumScore, int maximumScore) {
        List<Student> result = new ArrayList<>();
        for (Student student : sortedByScore) {
            int score = student.getScore();
            if (score < minimumScore) {
                break;
            }

            if (score <= maximumScore) {
                result.add(student);
            }
        }
        return result;
    }

    public Student firstAtLeast(int threshold) {
        Student firstStudent = null;
        if (!sortedByScore.isEmpty()) {
            Student highest = sortedByScore.get(0);
            if (highest.getScore() >= threshold) {
                firstStudent = highest;
            }
        }
        return firstStudent;
    }

    public List<Student> getAll() {
        return new ArrayList<>(sortedByScore);
    }

    public int size() {
        return sortedByScore.size();
    }
}