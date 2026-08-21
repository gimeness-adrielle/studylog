package me.gimenez.studylog;

import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import me.gimenez.studylog.ai.AiService;
import me.gimenez.studylog.dtos.StudyNoteRequestDTO;
import me.gimenez.studylog.dtos.StudyNoteResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class StudyNoteController {

    private final StudyNoteService service;
    private final AiService aiService;

    @GetMapping
    public List<StudyNoteResponseDTO> listAll() {
        return service.findAll();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "audio/wav")
    ResponseEntity<Resource> createWithAi(@RequestParam("file") MultipartFile file) {
        var result = aiService.transcribe(file);

        var resource = aiService.synthesize(result);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("audio.wav")
                                .build()
                                .toString())
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.ok("Note deleted successfully.");
    }

}
