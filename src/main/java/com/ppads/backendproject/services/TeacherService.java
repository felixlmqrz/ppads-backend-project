package com.ppads.backendproject.services;

import com.ppads.backendproject.models.Teacher;
import com.ppads.backendproject.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    public List<Teacher> findAll() {
        return repository.findAll();
    }

    public Teacher findById(Long id) {
        Optional<Teacher> obj = repository.findById(id);
        return obj.get();
    }

    public Teacher insert(Teacher obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Teacher update(Long id, Teacher obj) {
        Teacher entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Teacher entity, Teacher obj) {
        entity.setTeacherName(obj.getTeacherName());
    }
}
