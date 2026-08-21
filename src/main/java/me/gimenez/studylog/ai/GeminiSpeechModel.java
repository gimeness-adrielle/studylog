package me.gimenez.studylog.ai;

import me.gimenez.studylog.ai.audio.WavConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ai.audio.tts.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Flux;
import tools.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;

import static java.util.Base64.getDecoder;

@Component
public class GeminiSpeechModel implements TextToSpeechModel {

    @Value("${spring.ai.google.genai.api-key}")
    private String apiKey;

    private final RestClient restClient = RestClient.create();

    @Override
    public byte[] call(String text) {
        TextToSpeechPrompt prompt = new TextToSpeechPrompt(text);
        return call(prompt).getResult().getOutput();
    }

    @Override
    public TextToSpeechResponse call(TextToSpeechPrompt prompt){
        String url = "https://generativelanguage.googleapis.com/v1beta/interactions";

        GeminiSpeechRequest request = new GeminiSpeechRequest(
                "gemini-3.1-flash-tts-preview",
                prompt.getInstructions().getText(),
                new ResponseFormat("audio"),
                new GenerationConfig(List.of(new SpeechConfig("Kore")))
        );

        var response = restClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .header("x-goog-api-key", apiKey)
                .retrieve()
                .body(JsonNode.class);

        var audioData = response.get("steps").get(0).get("content").get(0).get("data").asText();

        byte[] audioBytes = getDecoder().decode(audioData);

        try {
            byte[] wav = WavConverter.convert(audioBytes);
            var speechResult = new Speech(wav);
            return new TextToSpeechResponse(List.of(speechResult));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flux<TextToSpeechResponse> stream(TextToSpeechPrompt prompt) {
        return Flux.just(call(prompt));
    }
}
