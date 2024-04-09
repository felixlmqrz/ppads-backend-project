package com.ppads.backendproject.services;

import com.ppads.backendproject.models.Lesson;
import com.ppads.backendproject.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    private LessonRepository repository;

    public List<Lesson> findAll() {
        return repository.findAll();
    }

    public Lesson findById(Long id) {
        Optional<Lesson> obj = repository.findById(id);
        return obj.get();
    }

    public Lesson insert(Lesson obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Lesson update(Long id, Lesson obj) {
        Lesson entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Lesson entity, Lesson obj) {
        entity.setLessonDate(obj.getLessonDate());
    }
}
