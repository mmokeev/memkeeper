package ru.memkeeper.entities;

import java.util.List;

public class User {
    private long id;
    private String name;
    private String email;
    private String passwordHash;
    private List<Tab> tabs;
}
