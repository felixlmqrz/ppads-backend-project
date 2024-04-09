package com.ppads.backendproject.controllers;

import com.ppads.backendproject.models.Attendance;
import com.ppads.backendproject.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    @GetMapping
    public ResponseEntity<List<Attendance>> findAll() {
        List<Attendance> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{lessonId}/{studentId}")
    public ResponseEntity<Attendance> findById(@PathVariable Long lessonId, @PathVariable Long studentId) {
        Attendance obj = service.findById(lessonId, studentId);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Attendance> insert(@RequestBody Attendance obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lessonId}/{studentId}")
                .buildAndExpand(obj.getLesson().getId(), obj.getStudent().getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{lessonId}/{studentId}")
    public ResponseEntity<Void> delete(@PathVariable Long lessonId, @PathVariable Long studentId) {
        service.delete(lessonId, studentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{lessonId}/{studentId}")
    public ResponseEntity<Attendance> update(@PathVariable Long lessonId, @PathVariable Long studentId, @RequestBody Attendance obj) {
        obj = service.update(lessonId, studentId, obj);
        return ResponseEntity.ok().body(obj);
    }
}
