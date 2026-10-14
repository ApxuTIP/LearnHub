package com.example.learnhub.structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomLinkedList<T> implements Iterable<T> {

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> first;
    private int size;

    public void addFirst(T value) {
        Node<T> node = new Node<>(value);
        node.next = first;
        first = node;
        size++;
    }

    public T removeFirst() {
        if (first == null) {
            throw new NoSuchElementException("Список пуст");
        }
        T value = first.value;
        first = first.next;
        size--;
        return value;
    }

    public T getFirst() {
        if (first == null) {
            throw new NoSuchElementException("Список пуст");
        }
        return first.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException("Элементы закончились");
                }
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }
}