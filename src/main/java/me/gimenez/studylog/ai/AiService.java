package me.gimenez.studylog.ai;

import lombok.RequiredArgsConstructor;
import me.gimenez.studylog.StudyNoteService;
import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.content.Media;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AiService {
    private final ChatClient chatClient;
    private final TextToSpeechModel speechModel;
    private final StudyNoteService studyNoteService;

    public String transcribe(MultipartFile file){
        var resource = file.getResource();

        return chatClient.prompt()
                .user(user -> user
                        .text("Transcreva e interprete o áudio a seguir:")
                        .media(new Media(MimeTypeUtils.parseMimeType("audio/m4a"), resource)))
                .tools(studyNoteService)
                .call()
                .content();
    }

    public ByteArrayResource synthesize(String request) {
        byte [] audio = speechModel.call(request);

        return new ByteArrayResource(audio);
    }

}
