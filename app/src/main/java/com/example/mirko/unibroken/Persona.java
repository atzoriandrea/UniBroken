package com.example.mirko.unibroken;

import java.io.Serializable;

public class Persona implements Serializable{

    private int id;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private  boolean isRaga;

    public Persona(){
        this.username = "";
        this.password = "";
        this.isRaga = false;
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

    public void setRaga(boolean v){
        this.isRaga = v;
    }

    public  boolean getIsRaga() {return isRaga;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
