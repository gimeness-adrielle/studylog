package me.gimenez.studylog.dtos;

import org.springframework.ai.tool.annotation.ToolParam;

public record StudyNoteRequestDTO(
        @ToolParam(description = "Principal tópico abordado no estudo.")
        String topic,

        @ToolParam(description = "Contéudo do estudo: o que foi feito, o que aprendeu.")
        String content
) {
}
