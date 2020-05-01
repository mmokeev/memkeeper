package ru.memkeeper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.memkeeper.entities.Note;

import java.util.Comparator;
import java.util.List;

@Service
public class MainPageService {
    private final TabService tabService;

    @Autowired
    public MainPageService(TabService tabService) {
        this.tabService = tabService;
    }

    public List<Note> findNotes(String userId, Long tabId) {
        List<Note> notes = tabService.findTabAndMarkItAsActive(userId, tabId).getNotes();
        notes.sort(Comparator.comparing(Note::getCreatedAt));
        return notes;
    }
}
