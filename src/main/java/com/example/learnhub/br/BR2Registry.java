package com.example.learnhub.br;

import com.example.learnhub.model.Student;
import com.example.learnhub.structures.CustomBST;

import java.util.List;

public class BR2Registry {

    private final CustomBST<Integer, Student> studentsById = new CustomBST<>();

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