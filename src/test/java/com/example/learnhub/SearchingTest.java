package com.example.learnhub;

import com.example.learnhub.algorithms.Searching;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchingTest {

    private final List<Integer> sorted = List.of(1, 3, 5, 7, 9, 11);

    @Test
    void binarySearchFindsExistingElements() {
        assertEquals(0, Searching.binarySearch(sorted, Integer::intValue, 1));
        assertEquals(3, Searching.binarySearch(sorted, Integer::intValue, 7));
        assertEquals(5, Searching.binarySearch(sorted, Integer::intValue, 11));
    }

    @Test
    void binarySearchReturnsMinusOneForMissingElement() {
        assertEquals(-1, Searching.binarySearch(sorted, Integer::intValue, 4));
        assertEquals(-1, Searching.binarySearch(sorted, Integer::intValue, 100));
    }

    @Test
    void rangeQueryReturnsElementsInRange() {
        List<Integer> result = Searching.rangeQuery(sorted, Integer::intValue, 3, 9);
        assertEquals(List.of(3, 5, 7, 9), result);
    }
}