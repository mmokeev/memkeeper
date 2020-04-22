package ru.memkeeper.entities;

import java.util.List;

public class Tab {
    private long id;
    private String name;
    private List<Post> posts;

    public Tab(String name, List<Post> posts) {
        this.name = name;
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
