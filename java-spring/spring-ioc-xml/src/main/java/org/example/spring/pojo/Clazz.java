package org.example.spring.pojo;

import java.util.List;

//班级
public class Clazz {

    private int id;
    private String name;
    private List<Student> studentList;

    public Clazz(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Clazz() {
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

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentList=" + studentList +
                '}';
    }
}
