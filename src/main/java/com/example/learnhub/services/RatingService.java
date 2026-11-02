package com.example.learnhub.services;

import com.example.learnhub.algorithms.BinarySearch;
import com.example.learnhub.algorithms.Sorter;
import com.example.learnhub.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RatingService {
    private static class ByScoreDescendingThenIdAscendingComparator implements Comparator<Student> {

        @Override
        public int compare(Student firstStudent, Student secondStudent) {
            int scoreComparison = Integer.compare(
                    secondStudent.getScore(), firstStudent.getScore());
            if (scoreComparison != 0) {
                return scoreComparison;
            }
            return Integer.compare(
                    firstStudent.getStudentId(), secondStudent.getStudentId());
        }
    }

    private final List<Student> sortedByScore = new ArrayList<>();
    private final Comparator<Student> comparator = new ByScoreDescendingThenIdAscendingComparator();

    public void rebuild(List<Student> students) {
        sortedByScore.clear();
        sortedByScore.addAll(students);
        Sorter.mergeSort(sortedByScore, comparator);
    }

    public int placeOf(Student targetStudent) {
        int index = BinarySearch.binarySearch(sortedByScore, comparator, targetStudent);
        int place = BinarySearch.NOT_FOUND;
        if (index >= 0) {
            place = index + 1;
        }
        return place;
    }

    public List<Student> inScoreRange(int minimumScore, int maximumScore) {
        if (minimumScore > maximumScore) {
            return new ArrayList<>();
        }

        Student maxBoundStudent = new Student(Integer.MIN_VALUE, "", maximumScore);
        Student minBoundStudent = new Student(Integer.MAX_VALUE, "", minimumScore);

        int startIndex = BinarySearch.lowerBound(sortedByScore, comparator, maxBoundStudent);
        int endIndex = BinarySearch.upperBound(sortedByScore, comparator, minBoundStudent);

        if (startIndex >= endIndex) {
            return new ArrayList<>();
        }
        return new ArrayList<>(sortedByScore.subList(startIndex, endIndex));
    }

    public Student firstAtLeast(int threshold) {
        Student thresholdStudent = new Student(Integer.MAX_VALUE, "", threshold);
        int index = BinarySearch.lowerBound(sortedByScore, comparator, thresholdStudent);

        if (index < sortedByScore.size() && sortedByScore.get(index).getScore() >= threshold) {
            return sortedByScore.get(index);
        }
        return null;
    }

    public List<Student> getAll() {
        return new ArrayList<>(sortedByScore);
    }

    public int size() {
        return sortedByScore.size();
    }
}
