package com.example.learnhub.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Собственное бинарное дерево поиска.
 *
 * <p>Реализовано вручную, потому что по BR-2 запрещено использовать
 * {@code TreeMap} и {@code TreeSet}.
 *
 * @param <K> тип ключа, обязательно сравнимый
 * @param <V> тип значения
 */
public class CustomBST<K extends Comparable<K>, V> {

    /**
     * Узел дерева.
     *
     * @param <K> тип ключа
     * @param <V> тип значения
     */
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V> root;
    private int size;

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }
        int comparison = key.compareTo(node.key);
        if (comparison < 0) {
            node.left = put(node.left, key, value);
        } else if (comparison > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    public V get(K key) {
        Node<K, V> current = root;
        while (current != null) {
            int comparison = key.compareTo(current.key);
            if (comparison < 0) {
                current = current.left;
            } else if (comparison > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;
    }

    public void inOrder(Consumer<V> visitor) {
        inOrder(root, visitor);
    }

    private void inOrder(Node<K, V> node, Consumer<V> visitor) {
        if (node == null) {
            return;
        }
        inOrder(node.left, visitor);
        visitor.accept(node.value);
        inOrder(node.right, visitor);
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
        return height(root);
    }

    private int height(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
}