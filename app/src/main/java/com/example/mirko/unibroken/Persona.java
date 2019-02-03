package com.example.mirko.unibroken;

import java.io.Serializable;

public class Persona implements Serializable{

    private String username;
    private String password;

    public Persona(){
        this.username = "mirko";
        this.password = "passw";
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
