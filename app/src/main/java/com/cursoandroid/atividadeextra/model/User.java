package com.cursoandroid.atividadeextra.model;

public class User {
    private String login;
    private String id;
    private String avatar_url;
    private String name;

    public User(String login, String id, String avatar_url, String name) {
        this.login = login;
        this.id = id;
        this.avatar_url = avatar_url;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
