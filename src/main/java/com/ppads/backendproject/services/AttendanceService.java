package com.ppads.backendproject.services;

import com.ppads.backendproject.models.Attendance;
import com.ppads.backendproject.models.Lesson;
import com.ppads.backendproject.models.Student;
import com.ppads.backendproject.models.pk.AttendancePK;
import com.ppads.backendproject.repositories.AttendanceRepository;
import com.ppads.backendproject.repositories.LessonRepository;
import com.ppads.backendproject.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService{

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

    public Attendance findById(Long lessonId, Long studentId) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        Student student = studentRepository.findById(studentId).get();

        AttendancePK attendancePK = new AttendancePK();
        attendancePK.setStudent(student);
        attendancePK.setLesson(lesson);

        Optional<Attendance> obj = attendanceRepository.findById(attendancePK);
        return obj.get();
    }

    public Attendance insert(Attendance obj) {
        return attendanceRepository.save(obj);
    }

    public void delete(Long lessonId, Long studentId) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        Student student = studentRepository.findById(studentId).get();

        AttendancePK attendancePK = new AttendancePK();
        attendancePK.setStudent(student);
        attendancePK.setLesson(lesson);

        attendanceRepository.deleteById(attendancePK);
    }

    public Attendance update(Long lessonId, Long studentId, Attendance obj) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        Student student = studentRepository.findById(studentId).get();

        AttendancePK attendancePK = new AttendancePK();
        attendancePK.setStudent(student);
        attendancePK.setLesson(lesson);

        Attendance entity = attendanceRepository.getReferenceById(attendancePK);
        updateData(entity, obj);
        return attendanceRepository.save(entity);
    }

    private void updateData(Attendance entity, Attendance obj) {
        entity.setPresence(obj.getPresence());
    }
}
