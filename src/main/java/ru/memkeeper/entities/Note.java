package ru.memkeeper.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "notes")
@SequenceGenerator(name = "notes_pk_seq", sequenceName = "notes_pk_seq")
public class Note {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(generator = "notes_pk_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @ManyToOne
    private Tab tab;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "text")
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Optional<String> getText() {
        return Optional.ofNullable(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }
}
