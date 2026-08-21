package me.gimenez.studylog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyNoteRepository extends JpaRepository<StudyNote, Long> {

}
