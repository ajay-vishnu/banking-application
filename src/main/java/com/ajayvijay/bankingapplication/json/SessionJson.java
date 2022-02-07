package com.ajayvijay.bankingapplication.json;

public class SessionJson {
    private String username;
    private String password;

    public SessionJson() {
    }

    public SessionJson(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
