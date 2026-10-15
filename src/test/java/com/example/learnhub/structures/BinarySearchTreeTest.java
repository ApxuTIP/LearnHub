package com.example.learnhub.structures;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeTest {

    @Test
    void putAndGetReturnStoredValue() {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        tree.put(5, "пять");
        assertEquals("пять", tree.get(5));
    }

    @Test
    void getMissingKeyReturnsNull() {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        tree.put(5, "пять");
        assertNull(tree.get(99));
    }

    @Test
    void toSortedListReturnsAscendingOrder() {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        tree.put(5, "пять");
        tree.put(2, "два");
        tree.put(8, "восемь");
        tree.put(1, "один");
        assertEquals(List.of("один", "два", "пять", "восемь"), tree.toSortedList());
    }

    @Test
    void heightStaysLogarithmicForShuffledKeys() {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        int count = 1000;
        for (int key = 0; key < count; key++) {
            int shuffledKey = (key * 7919) % count;
            tree.put(shuffledKey, shuffledKey);
        }
        assertTrue(tree.height() < 50,
                "Высота дерева должна быть около log2(n), но была " + tree.height());
    }

    @Test
    void insertingManyKeysDoesNotOverflow() {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        for (int key = 1; key <= 10_000; key++) {
            int shuffledKey = (key * 7919) % 10_000;
            tree.put(shuffledKey, shuffledKey);
        }
        assertEquals(10_000, tree.size());
    }
}