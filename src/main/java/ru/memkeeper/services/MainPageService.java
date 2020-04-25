package ru.memkeeper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.memkeeper.entities.Note;

import java.util.List;

@Service
public class MainPageService {
    private final TabService tabService;

    @Autowired
    public MainPageService(TabService tabService) {
        this.tabService = tabService;
    }

    public List<Note> findNotes(String userId, Long tabId) {
        return tabService.findTabAndMarkItAsActive(userId, tabId).getNotes();
    }
}
