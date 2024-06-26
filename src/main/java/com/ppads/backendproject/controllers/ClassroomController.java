package com.ppads.backendproject.controllers;

import com.ppads.backendproject.models.Classroom;
import com.ppads.backendproject.services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService service;

    @GetMapping
    public ResponseEntity<List<Classroom>> findAll() {
        List<Classroom> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Classroom> findById(@PathVariable Long id) {
        Classroom obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Classroom> insert(@RequestBody Classroom obj) {
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
    public ResponseEntity<Classroom> update(@PathVariable Long id, @RequestBody Classroom obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
