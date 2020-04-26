package ru.memkeeper.domain;

import java.util.Date;
import java.util.Optional;

public class Note {
    private long id;
    private String title;
    private Date createdAt;
    private Optional<String> text;

    public Note(long id, String title, Date createdAt, Optional<String> text) {
        this.title = title;
        this.createdAt = createdAt;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getText() {
        return text.orElse("");
    }
}