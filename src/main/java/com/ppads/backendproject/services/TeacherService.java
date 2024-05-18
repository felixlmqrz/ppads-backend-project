package com.ppads.backendproject.services;

import com.ppads.backendproject.dto.TeacherDTO;
import com.ppads.backendproject.models.Classroom;
import com.ppads.backendproject.models.Subject;
import com.ppads.backendproject.models.Teacher;
import com.ppads.backendproject.repositories.ClassroomRepository;
import com.ppads.backendproject.repositories.TeacherRepository;
import com.ppads.backendproject.services.exceptions.DatabaseException;
import com.ppads.backendproject.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Teacher findById(Long id) {
        Optional<Teacher> obj = teacherRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Teacher insert(TeacherDTO objDto) {
        Classroom classroom = classroomRepository.findById(objDto.classroomId())
                .orElseThrow(() -> new ResourceNotFoundException(objDto.classroomId()));

        Teacher obj = new Teacher(objDto.id(), objDto.teacherName(), null);
        obj.setSubject(new Subject(null, objDto.subjectName(), classroom));
        return teacherRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            teacherRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Teacher update(Long id, TeacherDTO objDto) {
        try {
            Teacher entity = teacherRepository.getReferenceById(id);
            updateData(entity, objDto);
            return teacherRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Teacher entity, TeacherDTO objDto) {
        entity.setTeacherName(objDto.teacherName());
    }
}
