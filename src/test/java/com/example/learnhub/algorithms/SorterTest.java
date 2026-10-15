package com.example.learnhub.algorithms;

import com.example.learnhub.utils.Comparators;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SorterTest {

    private static final Comparators.IntegerAscending ASCENDING =
            new Comparators.IntegerAscending();

    @Test
    void mergeSortSortsRandomData() {
        List<Integer> data = randomList(1000, 42);
        List<Integer> expected = new ArrayList<>(data);
        expected.sort(Integer::compare);

        Sorter.mergeSort(data, ASCENDING);
        assertEquals(expected, data);
    }

    @Test
    void insertionSortSortsSmallData() {
        List<Integer> data = List.of(5, 2, 8, 1, 9, 3);
        List<Integer> mutable = new ArrayList<>(data);
        Sorter.insertionSort(mutable, ASCENDING);
        assertEquals(List.of(1, 2, 3, 5, 8, 9), mutable);
    }

    @Test
    void adaptiveSortHandlesSmallList() {
        List<Integer> data = new ArrayList<>(List.of(3, 1, 2));
        Sorter.adaptiveSort(data, ASCENDING);
        assertEquals(List.of(1, 2, 3), data);
    }

    @Test
    void adaptiveSortHandlesLargeList() {
        List<Integer> data = randomList(1000, 7);
        List<Integer> expected = new ArrayList<>(data);
        expected.sort(Integer::compare);

        Sorter.adaptiveSort(data, ASCENDING);
        assertEquals(expected, data);
    }

    @Test
    void sortingEmptyListDoesNothing() {
        List<Integer> data = new ArrayList<>();
        Sorter.mergeSort(data, ASCENDING);
        assertEquals(0, data.size());
    }

    @Test
    void sortingSingleElementDoesNothing() {
        List<Integer> data = new ArrayList<>(List.of(42));
        Sorter.mergeSort(data, ASCENDING);
        assertEquals(List.of(42), data);
    }

    private List<Integer> randomList(int size, long seed) {
        Random random = new Random(seed);
        List<Integer> data = new ArrayList<>(size);
        for (int index = 0; index < size; index++) {
            data.add(random.nextInt(1_000_000));
        }
        return data;
    }
}