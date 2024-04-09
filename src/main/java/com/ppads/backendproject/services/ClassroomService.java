package com.ppads.backendproject.services;

import com.ppads.backendproject.models.Classroom;
import com.ppads.backendproject.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository repository;

    public List<Classroom> findAll() {
        return repository.findAll();
    }

    public Classroom findById(Long id) {
        Optional<Classroom> obj = repository.findById(id);
        return obj.get();
    }

    public Classroom insert(Classroom obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Classroom update(Long id, Classroom obj) {
        Classroom entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Classroom entity, Classroom obj) {
        entity.setClassroomName(obj.getClassroomName());
    }
}
