package ru.memkeeper.helpers;

import ru.memkeeper.entities.Note;
import ru.memkeeper.entities.Tab;
import ru.memkeeper.data.NoteData;
import ru.memkeeper.data.TabData;

public class Converter {

    public static NoteData convertNoteToData(Note note) {
        return new NoteData.Builder()
                .id(note.getId())
                .title(note.getTitle())
                .createdAt(note.getCreatedAt())
                .text(note.getText())
                .build();
    }

    public static TabData convertTabToTabData(Tab tab) {
        return new TabData.Builder()
                .id(tab.getId())
                .name(tab.getName())
                .isActive(tab.getActive())
                .build();
    }
}
