package com.ppads.backendproject.dto;

import java.time.LocalDate;

public record LessonDTO(Long id, LocalDate lessonDate, Long subjectId) {
}
