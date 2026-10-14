package com.example.learnhub.structures;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Consumer;

public class CustomBST<K extends Comparable<K>, V> {

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

    private Node<K, V> root;
    private int size;

    public void put(K key, V value) {
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            return;
        }

        Node<K, V> current = root;
        boolean inserted = false;
        while (!inserted) {
            int comparison = key.compareTo(current.key);
            if (comparison < 0) {
                if (current.left == null) {
                    current.left = new Node<>(key, value);
                    size++;
                    inserted = true;
                } else {
                    current = current.left;
                }
            } else if (comparison > 0) {
                if (current.right == null) {
                    current.right = new Node<>(key, value);
                    size++;
                    inserted = true;
                } else {
                    current = current.right;
                }
            } else {
                current.value = value;
                inserted = true;
            }
        }
    }

    public V get(K key) {
        Node<K, V> current = root;
        V result = null;
        boolean found = false;
        while (current != null && !found) {
            int comparison = key.compareTo(current.key);
            if (comparison < 0) {
                current = current.left;
            } else if (comparison > 0) {
                current = current.right;
            } else {
                result = current.value;
                found = true;
            }
        }
        return result;
    }

    public void inOrder(Consumer<V> visitor) {
        Deque<Node<K, V>> stack = new ArrayDeque<>();
        Node<K, V> current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.addLast(current);
                current = current.left;
            }
            Node<K, V> node = stack.removeLast();
            visitor.accept(node.value);
            current = node.right;
        }
    }

    public List<V> toSortedList() {
        List<V> result = new ArrayList<>();
        inOrder(result::add);
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
            Node<K, V> node = nodes.removeLast();
            int depth = depths.removeLast();
            if (depth > maximumDepth) {
                maximumDepth = depth;
            }
            if (node.left != null) {
                nodes.addLast(node.left);
                depths.addLast(depth + 1);
            }
            if (node.right != null) {
                nodes.addLast(node.right);
                depths.addLast(depth + 1);
            }
        }
        return maximumDepth;
    }
}