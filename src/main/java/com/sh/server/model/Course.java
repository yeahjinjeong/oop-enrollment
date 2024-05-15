package com.sh.server.model;

import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private int capacity;
    private int credit;
    private String name;
    private String professor;
    private String major;
    private int currentCapacity;

    public Course(int id, int capacity, int credit, String name, String professor, String major, int currentCapacity) {
        this.id = id;
        this.capacity = capacity;
        this.credit = credit;
        this.name = name;
        this.professor = professor;
        this.major = major;
        this.currentCapacity = currentCapacity;
    }

    public void setCurrentCapacity(String mark) {
        if (mark.equals("+")) {
            this.currentCapacity += 1;
        } else if (mark.equals("-")) {
            this.currentCapacity -= 1;
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", credit=" + credit +
                ", name='" + name + '\'' +
                ", professor='" + professor + '\'' +
                ", major='" + major + '\'' +
                ", currentCapacity=" + currentCapacity +
                "} \n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }
}
