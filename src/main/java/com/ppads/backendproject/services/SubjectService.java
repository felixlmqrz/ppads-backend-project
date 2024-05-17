package com.ppads.backendproject.services;

import com.ppads.backendproject.models.Subject;
import com.ppads.backendproject.repositories.SubjectRepository;
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
public class SubjectService {

    @Autowired
    private SubjectRepository repository;

    public List<Subject> findAll() {
        return repository.findAll();
    }

    public Subject findById(Long id) {
        Optional<Subject> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Subject insert(Subject obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Subject update(Long id, Subject obj) {
        try {
            Subject entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Subject entity, Subject obj) {
        entity.setSubjectName(obj.getSubjectName());
    }
}
