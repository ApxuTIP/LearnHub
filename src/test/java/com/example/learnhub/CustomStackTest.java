package com.example.learnhub;

import com.example.learnhub.structures.CustomStack;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomStackTest {

    @Test
    void popReturnsElementsInReverseOrder() {
        CustomStack<Integer> stack = new CustomStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void popFromEmptyStackThrowsException() {
        CustomStack<String> stack = new CustomStack<>();
        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    void peekReturnsTopWithoutRemoving() {
        CustomStack<String> stack = new CustomStack<>();
        stack.push("x");
        assertEquals("x", stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void peekOnEmptyStackReturnsNull() {
        CustomStack<String> stack = new CustomStack<>();
        assertNull(stack.peek());
    }
}