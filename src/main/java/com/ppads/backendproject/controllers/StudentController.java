package com.ppads.backendproject.controllers;

import com.ppads.backendproject.models.Student;
import com.ppads.backendproject.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        List<Student> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        Student obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Student> insert(@RequestBody Student obj) {
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
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
