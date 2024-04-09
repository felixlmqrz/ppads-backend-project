package com.ppads.backendproject.services;

import com.ppads.backendproject.models.Class;
import com.ppads.backendproject.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository repository;

    public List<Class> findAll() {
        return repository.findAll();
    }

    public Class findById(Long id) {
        Optional<Class> obj = repository.findById(id);
        return obj.get();
    }

    public Class insert(Class obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Class update(Long id, Class obj) {
        Class entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Class entity, Class obj) {
        entity.setClassDate(obj.getClassDate());
    }
}
