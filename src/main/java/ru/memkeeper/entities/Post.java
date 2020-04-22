package ru.memkeeper.entities;

import java.time.LocalDateTime;

public class Post {
    private long id;
    private String header;
    private LocalDateTime dateTime;
    private String text;

    public Post(String header, LocalDateTime dateTime, String text) {
        this.header = header;
        this.dateTime = dateTime;
        this.text = text;
    }

    public String getHeader() {
        return header;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getText() {
        return text;
    }
}
