package com.ppads.backendproject.services;

import com.ppads.backendproject.dto.StudentDTO;
import com.ppads.backendproject.models.Classroom;
import com.ppads.backendproject.models.Student;
import com.ppads.backendproject.repositories.ClassroomRepository;
import com.ppads.backendproject.repositories.StudentRepository;
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
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        Optional<Student> obj = studentRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Student insert(StudentDTO objDto) {
        Long classroomId = objDto.classroomId();
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ResourceNotFoundException(classroomId));

        Student obj = new Student(objDto.id(), objDto.studentName(), classroom);
        return studentRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Student update(Long id, StudentDTO objDto) {
        try {
            Student entity = studentRepository.getReferenceById(id);
            updateData(entity, objDto);
            return studentRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    private void updateData(Student entity, StudentDTO objDto) {
        entity.setStudentName(objDto.studentName());
    }
}
