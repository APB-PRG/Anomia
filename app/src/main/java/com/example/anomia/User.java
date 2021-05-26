package com.example.anomia;

public class User {
    private String email;
    private String password;
    private String username;
    private String repassword;
    private String id_user;

    public User() {
    }

    public User(String email, String password, String username, String repassword) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.repassword = repassword;
    }

    public User(String email, String password, String username, String repassword, String id_user) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.repassword = repassword;
        this.id_user = id_user;
    }

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
