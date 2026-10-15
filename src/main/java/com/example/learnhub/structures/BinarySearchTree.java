package com.example.learnhub.structures;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Consumer;

public class BinarySearchTree<K extends Comparable<K>, V> {
    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class ValueCollector<T> implements Consumer<T> {
        private final List<T> collectedValues;
        private ValueCollector(List<T> collectedValues) {
            this.collectedValues = collectedValues;
        }

        @Override
        public void accept(T value) {
            collectedValues.add(value);
        }
    }

    private Node<K, V> root;
    private int size;

    public void put(K key, V value) {
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            return;
        }
        Node<K, V> currentNode = root;
        boolean inserted = false;
        while (!inserted) {
            int comparison = key.compareTo(currentNode.key);
            if (comparison < 0) {
                if (currentNode.left == null) {
                    currentNode.left = new Node<>(key, value);
                    size++;
                    inserted = true;
                }

                else {
                    currentNode = currentNode.left;
                }
            }

            else if (comparison > 0) {
                if (currentNode.right == null) {
                    currentNode.right = new Node<>(key, value);
                    size++;
                    inserted = true;
                }

                else {
                    currentNode = currentNode.right;
                }
            }

            else {
                currentNode.value = value;
                inserted = true;
            }
        }
    }

    public V get(K key) {
        Node<K, V> currentNode = root;
        V result = null;
        boolean found = false;
        while (currentNode != null && !found) {
            int comparison = key.compareTo(currentNode.key);
            if (comparison < 0) {
                currentNode = currentNode.left;
            }

            else if (comparison > 0) {
                currentNode = currentNode.right;
            }

            else {
                result = currentNode.value;
                found = true;
            }
        }
        return result;
    }

    public void inOrder(Consumer<V> visitor) {
        Deque<Node<K, V>> stack = new ArrayDeque<>();
        Node<K, V> currentNode = root;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.addLast(currentNode);
                currentNode = currentNode.left;
            }
            Node<K, V> visitedNode = stack.removeLast();
            visitor.accept(visitedNode.value);
            currentNode = visitedNode.right;
        }
    }

    public List<V> toSortedList() {
        List<V> result = new ArrayList<>();
        inOrder(new ValueCollector<>(result));
        return result;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int height() {
        if (root == null) {
            return 0;
        }
        Deque<Node<K, V>> nodes = new ArrayDeque<>();
        Deque<Integer> depths = new ArrayDeque<>();
        nodes.addLast(root);
        depths.addLast(1);
        int maximumDepth = 0;
        while (!nodes.isEmpty()) {
            Node<K, V> currentNode = nodes.removeLast();
            int depth = depths.removeLast();
            if (depth > maximumDepth) {
                maximumDepth = depth;
            }

            if (currentNode.left != null) {
                nodes.addLast(currentNode.left);
                depths.addLast(depth + 1);
            }

            if (currentNode.right != null) {
                nodes.addLast(currentNode.right);
                depths.addLast(depth + 1);
            }
        }
        return maximumDepth;
    }
}