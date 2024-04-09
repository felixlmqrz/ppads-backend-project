package com.ppads.backendproject.controllers;

import com.ppads.backendproject.models.Class;
import com.ppads.backendproject.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/classes")
public class ClassController {

    @Autowired
    private ClassService service;

    @GetMapping
    public ResponseEntity<List<Class>> findAll() {
        List<Class> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Class> findById(@PathVariable Long id) {
        Class obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Class> insert(@RequestBody Class obj) {
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
    public ResponseEntity<Class> update(@PathVariable Long id, @RequestBody Class obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
