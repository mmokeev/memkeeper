package ru.memkeeper.entities;

import javax.persistence.*;

@Entity
@Table(name = "simple_table")
@SequenceGenerator(name = "simple_table_pk_seq", sequenceName = "simple_table_pk_seq")
public class SimpleEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(generator = "simple_table_pk_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "title")
    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
