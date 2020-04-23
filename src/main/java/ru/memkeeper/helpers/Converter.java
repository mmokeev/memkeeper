package ru.memkeeper.helpers;

import ru.memkeeper.entities.Note;
import ru.memkeeper.entities.Tab;
import ru.memkeeper.json.NoteJson;
import ru.memkeeper.json.TabJson;

public class Converter {

    public static NoteJson convertNoteToJson(Note note) {
        return new NoteJson.Builder()
                .id(note.getId())
                .title(note.getTitle())
                .createdAt(note.getCreatedAt())
                .text(note.getText())
                .build();
    }

    public static TabJson convertTabToJson(Tab tab) {
        return new TabJson.Builder()
                .id(tab.getId())
                .name(tab.getName())
                .isActive(tab.getActive())
                .build();
    }
}
