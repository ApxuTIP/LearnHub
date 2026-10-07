package com.example.learnhub.br;

import com.example.learnhub.algorithms.Searching;
import com.example.learnhub.algorithms.Sorting;
import com.example.learnhub.model.Student;

import java.util.ArrayList;
import java.util.List;

public class BR3Rating {

    private static final int ID_WEIGHT = 1000000;

    private final List<Student> sortedByScore = new ArrayList<>();

    public void rebuild(List<Student> students) {
        sortedByScore.clear();
        sortedByScore.addAll(students);
        Sorting.mergeSort(sortedByScore, this::scoreKey);
    }

    private int scoreKey(Student student) {
        return -student.getScore() * ID_WEIGHT + student.getId();
    }

    public int placeOf(Student target) {
        int index = Searching.binarySearch(sortedByScore, this::scoreKey, scoreKey(target));
        if (index < 0) {
            return -1;
        }
        return index + 1;
    }

    public List<Student> inScoreRange(int low, int high) {
        int fromKey = -high * ID_WEIGHT;
        int toKey = -low * ID_WEIGHT + (ID_WEIGHT - 1);
        return Searching.rangeQuery(sortedByScore, this::scoreKey, fromKey, toKey);
    }

    public Student firstAtLeast(int threshold) {
        int fromKey = -threshold * ID_WEIGHT + (ID_WEIGHT - 1);
        int index = Searching.lowerBound(sortedByScore, this::scoreKey, fromKey);
        while (index < sortedByScore.size()
                && sortedByScore.get(index).getScore() < threshold) {
            index++;
        }
        if (index >= sortedByScore.size()) {
            return null;
        }
        return sortedByScore.get(index);
    }

    public List<Student> all() {
        return new ArrayList<>(sortedByScore);
    }

    public int size() {
        return sortedByScore.size();
    }
}
