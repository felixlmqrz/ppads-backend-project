package com.ppads.backendproject.repositories;

import com.ppads.backendproject.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByLessonDate(LocalDate date);
}
