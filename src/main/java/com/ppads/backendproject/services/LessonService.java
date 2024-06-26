package com.ppads.backendproject.services;

import com.ppads.backendproject.dto.AttendanceDTO;
import com.ppads.backendproject.dto.LessonDTO;
import com.ppads.backendproject.models.*;
import com.ppads.backendproject.repositories.*;
import com.ppads.backendproject.services.exceptions.DatabaseException;
import com.ppads.backendproject.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Lesson findById(Long id) {
        Optional<Lesson> obj = lessonRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Lesson> findByLessonDate(LocalDate date) {
        return lessonRepository.findByLessonDate(date);
    }

    public Lesson insert(LessonDTO objDto) {
        Long subjectId = objDto.subjectId();
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(subjectId));

        Classroom classroom = subject.getClassroom();

        Lesson obj = new Lesson(objDto.id(), objDto.lessonDate(), subject);

        obj = lessonRepository.save(obj);

        for (Student student : classroom.getStudents()) {
            Attendance attendance = new Attendance(obj, student, true);
            attendanceRepository.save(attendance);
            obj.getAttendances().add(attendance);
        }

        return lessonRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            lessonRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Lesson update(Long id, Lesson obj) {
        try {
            Lesson entity = lessonRepository.getReferenceById(id);
            updateData(entity, obj);
            return lessonRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void takeAttendance(Long id, List<AttendanceDTO> attendanceDtoList) {
        Lesson lesson = lessonRepository.getReferenceById(id);

        for (AttendanceDTO attendanceDto : attendanceDtoList) {
            Long studentId = attendanceDto.studentId();
            Boolean presence = attendanceDto.presence();

            Student student = studentRepository.getReferenceById(studentId);

            Attendance attendance = new Attendance(lesson, student, presence);
            attendanceRepository.save(attendance);
        }
    }

    private void updateData(Lesson entity, Lesson obj) {
        entity.setLessonDate(obj.getLessonDate());
    }
}
