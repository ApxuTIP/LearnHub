package com.example.learnhub.br;

import com.example.learnhub.model.Student;
import com.example.learnhub.structures.CustomBST;

import java.util.List;

public class BR2Registry {

    private final CustomBST<Integer, Student> byId = new CustomBST<>();

    public void add(Student student) {
        byId.put(student.getId(), student);
    }

    public Student find(int id) {
        return byId.get(id);
    }

    public List<Student> allSorted() {
        return byId.toSortedList();
    }

    public int size() {
        return byId.size();
    }

    public int treeHeight() {
        return byId.height();
    }
}