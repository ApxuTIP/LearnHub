package com.example.learnhub.structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {
    private final LinkedList<T> elements = new LinkedList<>();

    public void push(T value) {
        elements.addFirst(value);
    }

    public T pop() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Стек пуст");
        }
        return elements.removeFirst();
    }

    public T peek() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Стек пуст");
        }
        return elements.getFirst();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }
}