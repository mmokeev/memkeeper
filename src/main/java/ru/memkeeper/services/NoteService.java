package ru.memkeeper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.memkeeper.entities.Note;
import ru.memkeeper.repositories.NotesRepository;
import ru.memkeeper.services.internal.TabComponent;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class NoteService {
    private final TabComponent tabComponent;
    private final NotesRepository notesRepository;

    @Autowired
    public NoteService(TabComponent tabComponent, NotesRepository notesRepository) {
        this.tabComponent = tabComponent;
        this.notesRepository = notesRepository;
    }

    @Transactional
    public Note addNewNote(String userId, Long tabId, String title, String text, LocalDateTime createdAt) {
        Note note = new Note();
        note.setUserId(userId);
        note.setTitle(title);
        note.setText(text);
        note.setTab(tabComponent.findTab(userId, tabId));
        if (createdAt != null) {
            note.steCreatedAt(createdAt);
        }

        return notesRepository.save(note);
    }

    @Transactional
    public void deleteNote(String userId, Long noteId) {
        notesRepository.deleteByUserIdAndNoteId(userId, noteId);
    }
}
