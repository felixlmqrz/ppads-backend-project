package com.ppads.backendproject.repositories;

import com.ppads.backendproject.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
