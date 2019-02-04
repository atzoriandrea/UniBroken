package com.example.mirko.unibroken;

import java.io.Serializable;

public class Segnalazione implements Serializable {
    private int autore;
    private String image;
    private String testo;
    public Segnalazione(){

    }

    public int getAutore() {
        return autore;
    }

    public void setAutore(int autore) {
        this.autore = autore;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}
