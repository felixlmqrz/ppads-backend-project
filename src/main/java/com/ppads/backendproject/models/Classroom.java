package com.ppads.backendproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_classroom")
public class Classroom implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String classroomName;

    @JsonIgnore
    @OneToMany(mappedBy = "classroom")
    private List<Subject> subjects = new ArrayList<>();

    public Classroom() {
    }

    public Classroom(Long id, String classroomName) {
        this.id = id;
        this.classroomName = classroomName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classroom classroom = (Classroom) o;
        return Objects.equals(id, classroom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
