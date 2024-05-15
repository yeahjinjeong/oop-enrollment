package com.sh.server.repository;

import com.sh.server.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    public List<Student> createStudent() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(19001, "정예진", "컴퓨터공학과", true, 0));
        students.add(new Student(20002, "김나경", "기계공학과", true, 0));
        students.add(new Student(21003, "이영우", "전자공학과", false, 0));
        students.add(new Student(22004, "정성연", "응용물리학과", true, 0));

        return students;
    }

    public void studentsToFile(List<Student> students) {
        File file = new File("/Users/jeong-yejin/2024SSGI&C/oop2-oops-enrollment-jyj/src", "studentslist.ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> fileToStudents() {
        File file = new File("/Users/jeong-yejin/2024SSGI&C/oop2-oops-enrollment-jyj/src", "studentslist.ser");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            return (List<Student>) obj;
        } catch (IOException | ClassNotFoundException e) {
            return createStudent();
        }
    }
}
