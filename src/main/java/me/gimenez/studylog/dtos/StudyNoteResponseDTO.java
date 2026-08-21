package me.gimenez.studylog.dtos;

import java.time.LocalDateTime;

public record StudyNoteResponseDTO(
        Long id,
        String topic,
        String content,
        LocalDateTime createdAt
) {
}
