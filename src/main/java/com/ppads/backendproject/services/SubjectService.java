package com.ppads.backendproject.services;

import com.ppads.backendproject.models.Subject;
import com.ppads.backendproject.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository repository;

    public List<Subject> findAll() {
        return repository.findAll();
    }

    public Subject findById(Long id) {
        Optional<Subject> obj = repository.findById(id);
        return obj.get();
    }

    public Subject insert(Subject obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Subject update(Long id, Subject obj) {
        Subject entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Subject entity, Subject obj) {
        entity.setSubjectName(obj.getSubjectName());
    }
}
