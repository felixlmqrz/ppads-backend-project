package com.ppads.backendproject.models.pk;

import com.ppads.backendproject.models.Lesson;
import com.ppads.backendproject.models.Student;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AttendancePK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendancePK that = (AttendancePK) o;
        return Objects.equals(lesson, that.lesson) && Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lesson, student);
    }
}
