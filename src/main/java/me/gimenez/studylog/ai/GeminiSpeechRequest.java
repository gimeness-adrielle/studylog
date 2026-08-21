package me.gimenez.studylog.ai;

import java.util.List;

public record GeminiSpeechRequest(
        String model,
        String input,
        ResponseFormat response_format,
        GenerationConfig generation_config
) {
}

record ResponseFormat(
        String type
) {}

record GenerationConfig(
        List<SpeechConfig> speech_config
) {}

record SpeechConfig(
        String voice
){}
