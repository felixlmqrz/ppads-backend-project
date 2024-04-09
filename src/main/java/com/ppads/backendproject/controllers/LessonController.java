package com.ppads.backendproject.controllers;

import com.ppads.backendproject.models.Lesson;
import com.ppads.backendproject.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/lessons")
public class LessonController {

    @Autowired
    private LessonService service;

    @GetMapping
    public ResponseEntity<List<Lesson>> findAll() {
        List<Lesson> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Lesson> findById(@PathVariable Long id) {
        Lesson obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Lesson> insert(@RequestBody Lesson obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Lesson> update(@PathVariable Long id, @RequestBody Lesson obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}