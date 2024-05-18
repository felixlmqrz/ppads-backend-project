package com.ppads.backendproject.controllers;

import com.ppads.backendproject.dto.TeacherDTO;
import com.ppads.backendproject.models.Teacher;
import com.ppads.backendproject.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/teachers")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @GetMapping
    public ResponseEntity<List<Teacher>> findAll() {
        List<Teacher> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable Long id) {
        Teacher obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Teacher> insert(@RequestBody TeacherDTO objDto) {
        Teacher obj = service.insert(objDto);
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
    public ResponseEntity<Teacher> update(@PathVariable Long id, @RequestBody TeacherDTO objDto) {
        Teacher obj = service.update(id, objDto);
        return ResponseEntity.ok().body(obj);
    }
}
