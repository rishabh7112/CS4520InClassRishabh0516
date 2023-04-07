package com.example.cs4520_inclass_rishabh0516.inClass08;
// Rishabh Sahu
// Assignment #8

public class User {
    private String email;
    private String first_name;
    private String last_name;
    private String password;
    private String username;

    public User() {
        // Empty constructor needed for Firestore
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}

