package com.ppads.backendproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ppads.backendproject.models.pk.AttendancePK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_attendance")
public class Attendance implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AttendancePK id = new AttendancePK();

    private Boolean presence;

    public Attendance() {
    }

    public Attendance(Lesson lesson, Student student, Boolean presence) {
        id.setLesson(lesson);
        id.setStudent(student);
        this.presence = presence;
    }

    @JsonIgnore
    public Lesson getLesson() {
        return id.getLesson();
    }

    public void setLesson(Lesson lesson) {
        id.setLesson(lesson);
    }

    public Student getStudent() {
        return id.getStudent();
    }

    public void setStudent(Student student) {
        id.setStudent(student);
    }

    public Boolean getPresence() {
        return presence;
    }

    public void setPresence(Boolean presence) {
        this.presence = presence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
