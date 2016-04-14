package com.example.stiles.model;

/**
 * Created by stiles on 16/4/13.
 */
public class Class {
    public int id;
    private String class_name;
    private String teacher_name;
    private String classroom;
    private int week;
    private int start;
    private int length;

    public Class(){
    }

    public Class(String class_name, String teacher_name, String classroom, int week, int start, int length) {
        this.class_name = class_name;
        this.teacher_name = teacher_name;
        this.classroom = classroom;
        this.week = week;
        this.start = start;
        this.length = length;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
