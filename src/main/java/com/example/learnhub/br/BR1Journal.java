package com.example.learnhub.br;

import com.example.learnhub.model.Action;
import com.example.learnhub.structures.CustomStack;

import java.util.ArrayList;
import java.util.List;

public class BR1Journal {

    private final CustomStack<Action> history = new CustomStack<>();

    public void record(Action action) {
        history.push(action);
    }

    public String undoLast() {
        if (history.isEmpty()) {
            return null;
        }
        Action action = history.pop();
        action.undo();
        return action.getDescription();
    }

    public int size() {
        return history.size();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }

    public void clear() {
        history.clear();
    }

    public List<String> snapshot() {
        List<String> result = new ArrayList<>();
        for (Action action : history) {
            result.add(action.getDescription());
        }
        return result;
    }
}