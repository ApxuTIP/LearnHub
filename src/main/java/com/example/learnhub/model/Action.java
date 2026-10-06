package com.example.learnhub.model;

public class Action {

    private final String description;
    private final Runnable undo;

    public Action(String description, Runnable undo) {
        this.description = description;
        this.undo = undo;
    }

    public String getDescription() {
        return description;
    }

    public void undo() {
        undo.run();
    }

    @Override
    public String toString() {
        return description;
    }
}