package com.ppads.backendproject.services;

import com.ppads.backendproject.models.Student;
import com.ppads.backendproject.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findById(Long id) {
        Optional<Student> obj = repository.findById(id);
        return obj.get();
    }

    public Student insert(Student obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Student update(Long id, Student obj) {
        Student entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Student entity, Student obj) {
        entity.setStudentName(obj.getStudentName());
    }
}
