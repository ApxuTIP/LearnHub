package com.example.learnhub.structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private static class Node<T> {
        private T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> first;
    private int size;

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = first;
        first = newNode;
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

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> currentNode = first;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                if (currentNode == null) {
                    throw new NoSuchElementException("Элементы закончились");
                }
                T value = currentNode.value;
                currentNode = currentNode.next;
                return value;
            }
        };
    }
}