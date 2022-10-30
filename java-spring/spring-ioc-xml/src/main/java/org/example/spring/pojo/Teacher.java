package org.example.spring.pojo;

public class Teacher {
    private Integer id;
    private String tname;

    public Teacher(Integer id, String tname) {
        this.id = id;
        this.tname = tname;
    }

    public Teacher() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", tname='" + tname + '\'' +
                '}';
    }
}
