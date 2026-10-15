package com.example.learnhub.services;

import com.example.learnhub.model.Student;
import com.example.learnhub.structures.BinarySearchTree;

import java.util.List;

public class StudentRegistryService {
    private final BinarySearchTree<Integer, Student> studentsById = new BinarySearchTree<>();

    public void addStudent(Student student) {
        studentsById.put(student.getStudentId(), student);
    }

    public Student findById(int studentId) {
        return studentsById.get(studentId);
    }

    public List<Student> getAllSortedById() {
        return studentsById.toSortedList();
    }

    public int getStudentCount() {
        return studentsById.size();
    }

    public int getTreeHeight() {
        return studentsById.height();
    }
}