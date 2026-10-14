package com.example.learnhub.model;

public class Student implements Comparable<Student> {

    private final int studentId;
    private final String studentName;
    private int score;

    public Student(int studentId, String studentName, int score) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.score = score;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.studentId, other.studentId);
    }

    @Override
    public String toString() {
        return String.format("Student{studentId=%d, studentName='%s', score=%d}",
                studentId, studentName, score);
    }
}