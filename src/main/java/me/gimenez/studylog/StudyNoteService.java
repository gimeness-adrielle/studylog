package me.gimenez.studylog;

import lombok.RequiredArgsConstructor;
import me.gimenez.studylog.dtos.StudyNoteRequestDTO;
import me.gimenez.studylog.dtos.StudyNoteResponseDTO;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyNoteService {

    private final StudyNoteRepository repository;

    public List<StudyNoteResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(studyNote -> new StudyNoteResponseDTO(
                        studyNote.getId(),
                        studyNote.getTopic(),
                        studyNote.getContent(),
                        studyNote.getCreatedAt()
                )).toList();
    }

    @Tool(description = "Cria um log de estudos")
    public StudyNoteResponseDTO save(@ToolParam(description = "Representação de um log de estudos") StudyNoteRequestDTO request) {
        StudyNote studyNote = new StudyNote();
        studyNote.setTopic(request.topic());
        studyNote.setContent(request.content());

        StudyNote savedLog = repository.save(studyNote);

        return new StudyNoteResponseDTO(
                savedLog.getId(),
                savedLog.getTopic(),
                savedLog.getContent(),
                savedLog.getCreatedAt()
        );
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
