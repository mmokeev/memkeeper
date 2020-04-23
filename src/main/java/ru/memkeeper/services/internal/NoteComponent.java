package ru.memkeeper.services.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.memkeeper.repositories.NotesRepository;

@Component
public class NoteComponent {
    private final NotesRepository notesRepository;

    @Autowired
    public NoteComponent(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }


}
