package com.sh.server.repository;

import com.sh.server.model.Course;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * = id;
 *         this.capacity = capacity;
 *         this.credit = credit;
 *         this.name = name;
 *         this.professor = professor;
 *         this.major = major;
 *         this.currentCapacity = currentCapacity;
 */
public class CourseRepository {
    public List<Course> createCourse() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(1101, 50, 3, "기초 프로그래밍", "김철연", "컴퓨터공학과", 0));
        courses.add(new Course(1102, 13, 3, "응용 프로그래밍", "배수지", "컴퓨터공학과", 0));
        courses.add(new Course(1103, 32, 3, "자료구조", "김광동", "컴퓨터공학과", 0));
        courses.add(new Course(1204, 41, 3, "기초 회로설계", "김아현", "기계공학과", 20));
        courses.add(new Course(1205, 24, 3, "마이크로스트립회로설계", "우승진", "기계공학과", 0));
        courses.add(new Course(1306, 31, 3, "고전전자기학1", "강희진", "전자공학과", 0));
        courses.add(new Course(1307, 20, 3, "고전전자기학2", "김미래", "전자공학과", 0));
        courses.add(new Course(1408, 62, 3, "기초 물리화학", "정성동", "응용물리학과", 0));
        courses.add(new Course(1409, 44, 3, "응용 물리화학", "김재영", "응용물리학과", 0));
        courses.add(new Course(1501, 18, 2, "영어 교양 1", "Kathy", "영어영문학과", 0));
        courses.add(new Course(1502, 17, 2, "영어 교양 2", "장원훈", "영어영문학과", 0));
        courses.add(new Course(1601, 60, 1, "진로 탐색", "이수현", "기초교양학부", 34));

        return courses;
    }

    public void coursesToFile(List<Course> courses) {
        File file = new File("/Users/jeong-yejin/2024SSGI&C/oop2-oops-enrollment-jyj/src", "courseslist.ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Course> fileToCourses() {
        File file = new File("/Users/jeong-yejin/2024SSGI&C/oop2-oops-enrollment-jyj/src", "courseslist.ser");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            return (List<Course>) obj;
        } catch (IOException | ClassNotFoundException e) {
            return createCourse();
        }
    }
}
