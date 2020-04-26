package ru.memkeeper.domain;

import java.util.List;

public class Tab {
    private long id;
    private String name;
    private boolean isActive;
    private List<Note> notes;

    public Tab(long id, String name, boolean isActive, List<Note> notes) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}