package com.sh.server.model;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private String major;
    private boolean status;
    private int currentCredits;

    public Student(int id, String name, String major, boolean status, int currentCredits) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.status = status;
        this.currentCredits = currentCredits;
    }

    public void setCurrentCredits(int credits, String mark) {
        if (mark.equals("+")) {
            this.currentCredits += credits;
        } else if (mark.equals("-")) {
            this.currentCredits -= credits;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", status=" + status +
                ", currentCredits=" + currentCredits +
                '}';
    }
}
