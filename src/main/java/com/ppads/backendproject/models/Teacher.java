package com.ppads.backendproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.util.Objects;

public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String teacherName;

    @JsonIgnore
    @OneToOne
    @MapsId
    private Subject subject;

    public Teacher() {
    }

    public Teacher(Long id, String teacherName, Subject subject) {
        this.id = id;
        this.teacherName = teacherName;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
