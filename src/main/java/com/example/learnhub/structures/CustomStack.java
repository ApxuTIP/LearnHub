package com.example.learnhub.structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomStack<T> implements Iterable<T> {

    private final CustomLinkedList<T> list = new CustomLinkedList<>();

    public void push(T value) {
        list.addLast(value);
    }

    public T pop() {
        if (list.isEmpty()) {
            throw new NoSuchElementException("Стек пуст");
        }
        return list.removeLast();
    }

    public T peek() {
        return list.peekLast();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}